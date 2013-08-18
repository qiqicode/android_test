package code.three.contact;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import code.three.R;

/**
 * Created by qiqicode on 13-8-18.
 */
public class ContactActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_test);
        Button button = (Button)findViewById(R.id.contact_find_name_btn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        findNameByNum();
                    }
                }
        );

    }

    private void findNameByNum() {
        ContactAdapter contactAdapter = new ContactAdapter(this);
        EditText editText = (EditText)findViewById(R.id.contact_find_name_et);
        String tvString = editText.getText().toString();
        List<String> displayNameList = contactAdapter.getContactByNum(tvString);
        StringBuilder nameSB = new StringBuilder();
        for (String name : displayNameList) {
            nameSB.append(name);
            nameSB.append("\n");
        }

        if(nameSB.toString() == "") {
            nameSB.append("no data");
        }

        TextView tvResult = (TextView)findViewById(R.id.contact_find_name_tv);
        tvResult.setText(nameSB.toString());
    }
}
