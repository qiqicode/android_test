package code.three;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import code.three.activity_for_result.ResultTest;
import code.three.async_task_test.AsyncTaskTest;
import code.three.contact.ContactFindActivity;
import code.three.contact.ContactMainActivity;
import code.three.contact.ContactUseRawActivity;
import code.three.db.AdvancedSearchActivity;
import code.three.intent_test.IntentTest;
import code.three.json.JsonTest;
import code.three.mail_text_test.MailTextTest;
import code.three.read_app_file_test.ReadAppFile;
import code.three.service_test.ServiceTest;
import code.three.test1.Test1;
import code.three.test2.Test2;
import code.three.test3.Test3;
import code.three.test4.Test4;
import code.three.test5.Test5;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.test_1:
            {
                Intent intent = new Intent(MainActivity.this, Test1.class);
                startActivity(intent);
                break;
            }
            case R.id.test_2:
            {
                Intent intent = new Intent(MainActivity.this, Test2.class);
                startActivity(intent);
                break;
            }
            case R.id.test_3:
            {
                Intent intent = new Intent(MainActivity.this, Test3.class);
                startActivity(intent);
                break;
            }
            case R.id.test_4:
            {
                Intent intent = new Intent(MainActivity.this, Test4.class);
                startActivity(intent);
                break;
            }
            case R.id.test_5:
            {
                Intent intent = new Intent(MainActivity.this, Test5.class);
                startActivity(intent);
                break;
            }
            case R.id.test_6:
            {
                Intent intent = new Intent(MainActivity.this, IntentTest.class);
                startActivity(intent);
                break;
            }
            case R.id.test_7:
            {
                Intent intent = new Intent(MainActivity.this, MailTextTest.class);
                startActivity(intent);
                break;
            }
            case R.id.test_8:
            {
                Intent intent = new Intent(MainActivity.this, ResultTest.class);
                startActivity(intent);
                break;
            }
            case R.id.test_9:
            {
                Intent intent = new Intent(MainActivity.this, ServiceTest.class);
                startActivity(intent);
                break;
            }
            case R.id.test_10:
            {
                Intent intent = new Intent(MainActivity.this, AsyncTaskTest.class);
                startActivity(intent);
                break;
            }
            case R.id.test_11:
            {
                Intent intent = new Intent(MainActivity.this, ReadAppFile.class);
                startActivity(intent);
                break;
            }
            case R.id.test_12:
            {
                Intent intent = new Intent(MainActivity.this, AdvancedSearchActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.test_13:
            {
                Intent intent = new Intent(MainActivity.this, JsonTest.class);
                startActivity(intent);
                break;
            }
            case R.id.test_14:
            {
                Intent intent = new Intent(MainActivity.this, ContactMainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.test_15:
            {
                Intent intent = new Intent(MainActivity.this, ContactUseRawActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test_1).setOnClickListener(this);
        findViewById(R.id.test_2).setOnClickListener(this);
        findViewById(R.id.test_3).setOnClickListener(this);
        findViewById(R.id.test_4).setOnClickListener(this);
        findViewById(R.id.test_5).setOnClickListener(this);
        findViewById(R.id.test_6).setOnClickListener(this);
        findViewById(R.id.test_7).setOnClickListener(this);
        findViewById(R.id.test_8).setOnClickListener(this);
        findViewById(R.id.test_9).setOnClickListener(this);
        findViewById(R.id.test_10).setOnClickListener(this);
        findViewById(R.id.test_11).setOnClickListener(this);
        findViewById(R.id.test_12).setOnClickListener(this);
        findViewById(R.id.test_13).setOnClickListener(this);
        findViewById(R.id.test_14).setOnClickListener(this);
        findViewById(R.id.test_15).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
