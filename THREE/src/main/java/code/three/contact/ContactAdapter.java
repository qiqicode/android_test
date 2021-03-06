package code.three.contact;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
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

    /**
     * 创建一条联系人记录
     * @param contactInfo
     */
    public ContentProviderResult[] createContact(ContactInfo contactInfo) {
        try {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

            // 在accountName下插入一条联系人记录
            ops.add(
                    ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, contactInfo.getAccountType())
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, contactInfo.getAccountName())
                    .build()
            );

            // 为该联系人添加基本信息
            ops.add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contactInfo.getName())
                    .build()
            );

            // 为该联系人添加电话信息
            ops.add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, contactInfo.getMobilePhone())
                            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                            .build()
            );

            return this.context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getContactData(int contactId) {
        Uri uri = ContactsContract.Data.CONTENT_URI;
        Cursor cursor = this.context.getContentResolver().query(
                uri,
                new String[] {
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                },
                ContactsContract.Data._ID + "=?"
                      //  + " AND " + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
                ,
                new String[] {
                        String.valueOf(contactId)
                },
                null
        );
        List<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            int iNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = cursor.getString(iNumber);
            list.add(number);
        }

        return list;
    }

    /**
     * 查找联系人信息，通过RawContacts接口
     * @param account_name
     * @param account_type
     * @param name
     * @return
     */
    public List<String> getContactUseRawContacts(String account_name, String account_type, String name) {
        if(null == account_name || "".equals(account_name)) return new ArrayList<String>();
        if(null == account_type || "".equals(account_type)) return new ArrayList<String>();
        Uri uri = ContactsContract.RawContacts.CONTENT_URI.buildUpon()
                .appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_NAME, account_name)
                .appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_TYPE, account_type)
                .build();

        Cursor cursor = this.context.getContentResolver().query(
                uri,
                new String[]{
                        ContactsContract.RawContacts._ID,
                        ContactsContract.RawContacts.ACCOUNT_NAME,
                        ContactsContract.RawContacts.ACCOUNT_TYPE,
                        ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY
                },
                ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " like ? OR " + ContactsContract.RawContacts.Data._ID + "=? " ,
                new String[] {
                        "%" + name + "%",
                        name
                },
                null
                );

        int iID = cursor.getColumnIndex(ContactsContract.RawContacts._ID);
        int iAccountName = cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_NAME);
        int iAccountType = cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE);
        int iDisplayNamePrimary = cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY);
        String id;
        String accountName;
        String accountType;
        String displayNamePrimary;
        List<String> contactsList = new ArrayList<String>();
        while (cursor.moveToNext()) {
            id = cursor.getString(iID);
            accountName = cursor.getString(iAccountName);
            accountType = cursor.getString(iAccountType);
            displayNamePrimary = cursor.getString(iDisplayNamePrimary);
            StringBuilder sb = new StringBuilder();
            sb.append(id).append("|").append(accountName).append("|").append(accountType).append("|").append(displayNamePrimary);
            contactsList.add(sb.toString());
        }
        return contactsList;
    }

    /**
     * 通过电话号码查询联系人信息
     * @param phoneNum
     * @return
     */
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

    public List<String> getAllContactAccount() {
        Uri uri = ContactsContract.RawContacts.CONTENT_URI;
        Cursor cursor = this.context.getContentResolver().query(
                uri,
                new String[]{
                        ContactsContract.RawContacts.ACCOUNT_NAME,
                        ContactsContract.RawContacts.ACCOUNT_TYPE,
                        ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,
                        ContactsContract.RawContacts.STARRED
                },
                null,
                null,
                null
        );
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            sb.setLength(0);
            int iAccountName = cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_NAME);
            int iAccountType = cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE);
            int iDisplayNamePrimary = cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY);
            int iStarred = cursor.getColumnIndex(ContactsContract.RawContacts.STARRED);

            String accountName = cursor.getString(iAccountName);
            String accountType = cursor.getString(iAccountType);
            String displayNamePrimary = cursor.getString(iDisplayNamePrimary);
            Integer starred = cursor.getInt(iStarred);
            sb.append(accountName).append(",").append(accountType).append(",").append(displayNamePrimary).append(",").append(starred);
            list.add(sb.toString());
        }
        return list;
    }
}
