package code.three.service_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import code.three.R;

/**
 * Created by qiqicode on 13-8-8.
 */
public class StartTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_service_test);
        Intent intent = new Intent(this, TestService.class);
        startService(intent);
        Toast.makeText(StartTest.this, "Starting:" + ServiceTest.TEMP, Toast.LENGTH_SHORT).show();
    }
}
