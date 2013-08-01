package code.three.mail_text_test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import code.three.R;

/**
 * Created by qiqicode on 13-7-31.
 */
public class MailTextTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_text_test);

        findViewById(R.id.emailButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 单附件
//                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        // 多附件
                        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        emailIntent.setType("text/html");
                        emailIntent.putExtra(Intent.EXTRA_TITLE, "My Title");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Subject");

                        emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_text));

                        // 附件
                        File file = null;
                        ArrayList<Uri> uriList = null;
                        try {
                            EditText editText = (EditText)findViewById(R.id.email_attachment_location_text);
                            String attach_location = editText.getText().toString();
                            String[] attachArray = attach_location.split(",");
                            uriList = new ArrayList<Uri>();
                            for (String temp : attachArray)
                            {
                                if(null == temp || temp.isEmpty()) continue;
                                file = new File(temp.trim());
                                if(!file.exists()) {
                                    file = new File("/storage/sdcard0/genie_login_test.txt");
                                }
                                uriList.add(Uri.fromFile(file));
                            }
                        } catch (Exception e){
//                            file = new File("/storage/sdcard0/genie_login_test.txt");
                        }
                        // 单附件
//                        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                        // 多附件
                        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
                        startActivity(emailIntent);
                    }
                }
        );
    }
}
