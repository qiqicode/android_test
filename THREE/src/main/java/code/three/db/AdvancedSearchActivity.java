package code.three.db;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;

import code.three.R;

/**
 * Created by qiqicode on 13-8-16.
 */
public class AdvancedSearchActivity extends Activity {
    private DbAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        dbAdapter = new DbAdapter(this);
        try {
            dbAdapter.open();
        } catch (Exception e) {

        }

        if(dbAdapter.databaseCreated()) {
            dbAdapter.insertRow("test", "test example", "example_test@example.com");
            dbAdapter.insertRow("lorem", "lorem ipsum", "lorem.ipsum@example2.com");
            dbAdapter.insertRow("jdoe", "Jonh Doe", "j.doe@example.com");
        }

        Button button = (Button) findViewById(R.id.btnSearch);
        final EditText etSearch = (EditText) findViewById(R.id.etSearch);
        final TextView tvResults = (TextView) findViewById(R.id.tvResults);

        button.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<String> results = dbAdapter.search(etSearch.getText().toString());

                if(results.isEmpty()) {
                    tvResults.setText("No results found");
                } else {
                    Iterator<String> i = results.iterator();
                    tvResults.setText("");
                    while (i.hasNext()) {
                        tvResults.setText(tvResults.getText() + i.next() + "\n");
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        dbAdapter.close();
        super.onDestroy();
    }
}
