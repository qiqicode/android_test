package code.three.contact;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import code.three.R;

/**
 * Created by qiqicode on 13-8-18.
 */
public class ContactDataActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_data);

        Button button = (Button)findViewById(R.id.contact_data_btn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData();
                    }
                }
        );
    }

    private void getData() {
        TextView textView = (TextView)findViewById(R.id.contact_data_tv);
        ContactAdapter contactAdapter = new ContactAdapter(this);
        List<String> numberList = contactAdapter.getContactData(Integer.parseInt(textView.getText().toString()));

        if(null == numberList || numberList.size() < 1) {
            numberList.add("no data");
        }

        ListView listView = (ListView)findViewById(R.id.contact_data_lv);
        listView.setAdapter(new ArrayAdapter<String>(ContactDataActivity.this, R.layout.activity_async_list_item, numberList));
    }
}
