package code.three.contact;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import code.three.R;

/**
 * Created by qiqicode on 13-8-18.
 */
public class ContactUseRawActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_raw);

        Button button = (Button)findViewById(R.id.contact_raw_btn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getContactInfoByRawInterface();
                    }
                }
        );

        Button button2 = (Button)findViewById(R.id.contact_raw_account_btn);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getAllAccountInfo();
                    }
                }
        );
    }

    private void getAllAccountInfo() {
        ContactAdapter contactAdapter = new ContactAdapter(this);
        List<String> contactAccountList = contactAdapter.getAllContactAccount();

        if(contactAccountList.size() == 0) {
            contactAccountList.add("no data");
        }

        ListView lvResult = (ListView)findViewById(R.id.contact_raw_tv);
        lvResult.setAdapter(new ArrayAdapter<String>(ContactUseRawActivity.this, R.layout.activity_async_list_item, contactAccountList));
    }

    private void getContactInfoByRawInterface()
    {
        EditText editText = (EditText)findViewById(R.id.contact_raw_et);
        String[] accountInfo = editText.getText().toString().split(",");
        if(null == accountInfo || accountInfo.length < 3) return;
        String accountName = accountInfo[0];
        String accountType = accountInfo[1];
        String name = accountInfo[2];

//        String accountType = AccountManager.get(this).getAuthenticatorTypes()[0].type;
        ContactAdapter contactAdapter = new ContactAdapter(this);
        List<String> contactList = contactAdapter.getContactUseRawContacts(accountName, accountType, name);

        if(contactList.size() == 0) {
            contactList.add("no data");
        }

        ListView lvResult = (ListView)findViewById(R.id.contact_raw_tv);
        lvResult.setAdapter(new ArrayAdapter<String>(ContactUseRawActivity.this, R.layout.activity_async_list_item, contactList));
    }
}
