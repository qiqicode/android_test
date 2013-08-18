package code.three.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import code.three.R;

/**
 * Created by qiqicode on 13-8-18.
 */
public class ContactMainActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_test);

        findViewById(R.id.contact_find_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_find_btn : {
                Intent intent = new Intent(ContactMainActivity.this, ContactFindActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
