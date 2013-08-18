package code.three.contact;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiqicode on 13-8-18.
 */
public class ContactAdapter {
    private final Context context;
    public ContactAdapter(Context context) {
        this.context = context;
    }

    public List<String> getContactByNum(String phoneNum) {
        if(null == phoneNum || "".equals(phoneNum)) return new ArrayList<String>();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNum));
        Cursor result = this.context.getContentResolver().query(
                uri,
                new String[]{ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.DISPLAY_NAME},
                null,
                null,
                null
        );
        List<String> displayNameList = new ArrayList<String>();
        while (result.moveToNext()) {
            int iDisplayName = result.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String displayName = result.getString(iDisplayName);
            displayNameList.add(displayName);
        }
        return displayNameList;
    }
}
