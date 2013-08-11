package code.three.async_task_test;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import code.three.R;

/**
 * Created by qiqicode on 13-8-11.
 */
public class AsyncTaskTest extends Activity implements AdapterView.OnItemClickListener{
    ListView mListView;
    Button mButton1;
    Button mButton2;
    Button mButton3;

    protected static final int DIALOG_KEY = 0;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        this.setProgressBarIndeterminateVisibility(false);

        setContentView(R.layout.activity_async_task_test);

        mListView = (ListView)findViewById(R.id.listView1);
        mListView.setTextFilterEnabled(false);
        mListView.setOnItemClickListener(this);

        mButton1 = (Button)findViewById(R.id.button1);
        mButton2 = (Button)findViewById(R.id.button2);
        mButton3 = (Button)findViewById(R.id.button3);

        mButton1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoadRecipesTask1 mLoadRecipesTask = new LoadRecipesTask1();
                        mLoadRecipesTask.execute("http://androidcookbook.com/seam/resource/rest/recipe/list");

                    }
                }
        );

        mButton2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoadRecipesTask2 mLoadRecipesTask = new LoadRecipesTask2();
                        String url = "http://androidcookbook.com/seam/resource/rest/recipe/list";
                        showDialog(DIALOG_KEY);
                        mLoadRecipesTask.execute(url, url, url,url,url,url,url,url,url);
                    }
                }
        );

        mButton3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListView.setAdapter(null);
                    }
                }
        );
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Datum datum = (Datum)mListView.getItemAtPosition(position);
        Uri uri = Uri.parse("http://androidcookbook.com/Recipe.seam?recipeId=" + datum.getId());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        this.startActivity(intent);
    }

    protected class LoadRecipesTask1 extends AsyncTask<String, Void, ArrayList<Datum>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AsyncTaskTest.this.setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected void onPostExecute(ArrayList<Datum> datums) {
            super.onPostExecute(datums);
            mListView.setAdapter(new ArrayAdapter<Datum>(AsyncTaskTest.this, R.layout.activity_async_list_item, datums));
            AsyncTaskTest.this.setProgressBarIndeterminateVisibility(false);
        }

        @Override
        protected ArrayList<Datum> doInBackground(String... strings) {
            ArrayList<Datum> datumList = new ArrayList<Datum>();
            try{
                datumList = parse(strings[0]);
            } catch (IOException e){
                e.printStackTrace();
            }catch (XmlPullParserException e){
                e.printStackTrace();
            }
            return datumList;
        }
    }

    protected class LoadRecipesTask2 extends AsyncTask<String, Integer, ArrayList<Datum>>
    {
        @Override
        protected ArrayList<Datum> doInBackground(String... strings) {
            ArrayList<Datum> datumList = new ArrayList<Datum>();
            for(int i = 0; i < strings.length; i++)
            {
                try{
                    datumList = parse(strings[i]);
                    publishProgress((int)(((i + 1) / (float)strings.length ) * 100));
                } catch (IOException e){
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
            return datumList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Datum> datums) {
            super.onPostExecute(datums);
            mListView.setAdapter(new ArrayAdapter<Datum>(AsyncTaskTest.this, R.layout.activity_async_list_item, datums));
            mProgressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressDialog.setProgress(values[0]);
        }
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id)
        {
            case DIALOG_KEY:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setMessage("Retrieving recipes...");
                mProgressDialog.setCancelable(false);
                return mProgressDialog;
        }
        return null;
    }

    /**
     * parse xml file
     * @param url
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static ArrayList<Datum> parse(String url) throws IOException, XmlPullParserException
    {
        final ArrayList<Datum> results = new ArrayList<Datum>();
        URL input = new URL(url);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(input.openStream(), null);
        int eventType = xpp.getEventType();
        String currentTag = null;
        Integer id = null;
        String title = null;
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            if(eventType == XmlPullParser.START_TAG) {
                currentTag = xpp.getName();
            }
            else if (eventType == XmlPullParser.TEXT) {
                if("id".equals(currentTag)) {
                    id = Integer.parseInt(xpp.getText());
                }
                if("title".equals(currentTag)) {
                    title = xpp.getText();
                }
            }
            else if (eventType == XmlPullParser.END_TAG) {
                if("recipe".equals(xpp.getName())) {
                    results.add(new Datum(id,title));
                }
            }

            eventType = xpp.next();
        }

        return results;
    }

    /**
     * 简单的解析xml
     * @param url
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    protected static ArrayList<Datum> parse2(String url) throws IOException, XmlPullParserException
    {
        final  ArrayList results = new ArrayList<Datum>();
        URL input = new URL(url);

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(input.openStream(), null);
        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, null, "recipes");

        while (parser.nextTag() == XmlPullParser.START_TAG)
        {
            parser.require(XmlPullParser.START_TAG, null, "recipe");

            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "id");
            Integer id = Integer.valueOf(parser.next());
            parser.require(XmlPullParser.END_TAG, null, "id");

            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "title");
            String title = parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "title");

            parser.nextTag();
            parser.require(XmlPullParser.END_TAG, null, "recipe");

            results.add(new Datum(id, title));
        }
        parser.require(XmlPullParser.END_TAG, null, "recipes");

        return results;
    }
}
