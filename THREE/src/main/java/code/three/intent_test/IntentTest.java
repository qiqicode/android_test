package code.three.intent_test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import code.three.R;

/**
 * Created by qiqicode on 13-7-30.
 */
public class IntentTest extends Activity{
    int i;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(IntentTest.this, String.format("Activity %d result %d", requestCode, resultCode), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        findViewById(R.layout.activity_intent_test).
        setContentView(R.layout.activity_intent_test);

        findViewById(R.id.go_easy).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editText = (EditText)findViewById(R.id.textfield);
                        String uri = editText.getText().toString();

                        try{
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(IntentTest.this, "Error:" + e, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );


        findViewById(R.id.go_results).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editText = (EditText)findViewById(R.id.textfield);
                        String uri = editText.getText().toString();

                        try{
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                            startActivityForResult(intent, ++i);
                        } catch (Exception e) {
                            Toast.makeText(IntentTest.this, "Error: " + e, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );



    }
}
