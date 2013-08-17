package code.three.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import code.three.R;

/**
 * Created by qiqicode on 13-8-17.
 */
public class JsonTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_test);

        findViewById(R.id.btn_json).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        readJson();
                    }
                }
        );
    }

    private String getJsonString()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "John Doe");
            jsonObject.put("age", new Integer(25));
            jsonObject.put("address", "75 Ninth Avenue 2nd and 4th Floors New York, NY 10011");
            jsonObject.put("phone", "8367667829");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonObject.toString();
        }
        return jsonObject.toString();
    }

    private void readJson()
    {
        try {
            String jsonString = this.getJsonString();
            JSONObject jsonObject = new JSONObject(jsonString);
            String name = jsonObject.getString("name");
            String age = jsonObject.getString("age");
            String address = jsonObject.getString("address");
            String phone = jsonObject.getString("phone");

            StringBuilder jsonSB = new StringBuilder();
            jsonSB.append(name).append("\n").append(age).append("\n").append(address).append("\n").append(phone);
            TextView json = (TextView)findViewById(R.id.tv_json);
            json.setText(jsonSB.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
