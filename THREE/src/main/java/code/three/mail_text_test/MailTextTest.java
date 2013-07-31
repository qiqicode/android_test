package code.three.mail_text_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html");
                        emailIntent.putExtra(Intent.EXTRA_TITLE, "My Title");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Subject");

                        emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_text));
                        startActivity(emailIntent);
                    }
                }
        );
    }
}
