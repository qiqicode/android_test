package code.three.activity_for_result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import code.three.R;

/**
 * Created by qiqicode on 13-8-6.
 */
public class SubTest extends Activity {

    public static final String EXTRA_STRING_NAME = "extraStringName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_sub_test);
        this.pullResult();
    }

    private void pullResult()
    {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_STRING_NAME, "returnVauleAsString");

        setResult(Activity.RESULT_OK, intent);
        finishActivity(ResultTest.MY_REQUEST_CODE);
    }
}
