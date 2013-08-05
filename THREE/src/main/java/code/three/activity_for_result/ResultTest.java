package code.three.activity_for_result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import code.three.R;

/**
 * Created by qiqicode on 13-8-6.
 */
public class ResultTest extends Activity {
    public static final int MY_REQUEST_CODE = 123;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("ResultTest", "Retrieved Value is  ");
        if(requestCode == MY_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                final String value = data.getExtras().getString(SubTest.EXTRA_STRING_NAME);
                Log.v("ResultTest", "Retrieved Value is  " + value);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        findViewById(R.id.result_main).setOnClickListener (
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ResultTest.this, MainTest.class);
                        startActivity(intent);
                    }
                }
        );

        findViewById(R.id.result_sub).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ResultTest.this, SubTest.class);
                        startActivityForResult(intent, MY_REQUEST_CODE);
                    }
                }
        );
    }
}
