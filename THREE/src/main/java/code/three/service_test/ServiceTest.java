package code.three.service_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import code.three.R;

/**
 * Created by qiqicode on 13-8-7.
 */
public class ServiceTest extends Activity {
    public static int TEMP = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        final Intent intent = new Intent(this, TestService.class);
        findViewById(R.id.service_start).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(ServiceTest.this, StartTest.class);
//                        startActivity(intent);

                        startService(intent);
                        Toast.makeText(ServiceTest.this, "Starting:" + ServiceTest.TEMP, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        findViewById(R.id.service_stop).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(ServiceTest.this, StopTest.class);
//                        startActivity(intent);

                        stopService(intent);
                        Toast.makeText(ServiceTest.this, "Stopped:" + ServiceTest.TEMP, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
