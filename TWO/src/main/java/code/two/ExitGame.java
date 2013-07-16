package code.two;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by qiqicode on 13-7-14.
 */
public class ExitGame extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        finish();
//        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
