package code.three.contact;

import android.app.Activity;
import android.content.ContentProviderResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import code.three.R;

/**
 * Created by qiqicode on 13-8-22.
 */
public class ContactCreateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_create);

        Button button = (Button)findViewById(R.id.contact_create_go_btn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createContact();
                    }
                }
        );
    }

    private void createContact() {
        ContactAdapter contactAdapter = new ContactAdapter(this);
        ContactInfo contactInfo = new ContactInfo();
        ContentProviderResult[] contentProviderResults = contactAdapter.createContact(contactInfo);

        TextView tv = (TextView)findViewById(R.id.contact_create_result_tv);
        if(null != contentProviderResults && contentProviderResults.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (ContentProviderResult result : contentProviderResults) {
                sb.append(result.toString()).append("\n");
            }
//            ContentProviderResult result =  contentProviderResults[0];
            tv.setText(sb.toString());
        } else {
            tv.setText("fail!");
        }
    }
}
