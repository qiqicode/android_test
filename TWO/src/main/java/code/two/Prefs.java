package code.two;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by qiqicode on 13-7-12.
 */
public class Prefs extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
//            addPreferencesFromResource(R.xml.settings);
//        }
    }

//    @Override
//    public void onBuildHeaders(List<Header> target) {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            loadHeadersFromResource(R.xml.settings, target);
//        } else {
//            super.onBuildHeaders(target);
//        }
//    }
}
