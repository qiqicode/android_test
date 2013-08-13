package code.three.read_app_file_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import code.three.R;

/**
 * Created by qiqicode on 13-8-14.
 */
public class ReadAppFile extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_app_file);
        InputStreamReader is = new InputStreamReader(this.getResources().openRawResource(R.layout.activity_main));
        BufferedReader reader = new BufferedReader(is);
        StringBuilder finalText = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine())!= null) {
                finalText.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView fileTextView = (TextView)findViewById(R.id.fill_text);
        fileTextView.setText(finalText.toString());
    }
}
