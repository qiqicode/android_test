package code.three.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by qiqicode on 13-8-16.
 */
public class DbAdapter {

    public static final String APP_NAME = "AdvancedSearch";
    public static final String DATABASE_NAME = "AdvancedSearch_db";
    public static final  int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "example_tb1";
    public static final String KEY_USERNAME ="username";
    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_EMAIL = "email";
    public static long GENERIC_ERROR = -1;
    public static long GENERIC_NO_RESULTS = -2;
    public static long ROW_INSERT_FAILED = -3;

    private final Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase sqlDatabase;

    public DbAdapter(Context context) {
        this.context = context;
    }

    public long insertRow(String username, String fullname, String email) {
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, username);
            values.put(KEY_FULLNAME, fullname);
            values.put(KEY_EMAIL, email);

            return this.sqlDatabase.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e(APP_NAME, "An error occurred while inserting the row: " + e.toString(), e);
        }
        return ROW_INSERT_FAILED;
    }

    public LinkedList<String> search(String search) {
        LinkedList<String> results = new LinkedList<String>();
        Cursor cursor = null;
        try {
            cursor = this.sqlDatabase.query(true, TABLE_NAME, new String[] {
                    KEY_USERNAME, KEY_FULLNAME, KEY_EMAIL}, TABLE_NAME + " MATCH ?",
                    new String[] {search}, null, null, null, null);

            if(cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                int iUsername = cursor.getColumnIndex(KEY_USERNAME);
                int iFullname = cursor.getColumnIndex(KEY_FULLNAME);
                int iEmail = cursor.getColumnIndex(KEY_EMAIL);

                do {
                    results.add(
                            new String(
                                    "Username: " + cursor.getString(iUsername) +
                                    ", Fullname:" + cursor.getString(iFullname) +
                                    ", Email:" + cursor.getString(iEmail)
                            )
                    );
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(APP_NAME, "An error occurred while searching for " + search + ": " + e.toString(), e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return results;
    }






    public boolean open() throws SQLException {
        try {
            this.dbHelper = new DbHelper(this.context);
            this.sqlDatabase = this.dbHelper.getWritableDatabase();
            return this.sqlDatabase.isOpen();
        } catch (SQLiteException e) {
            throw e;
        }
    }

    public boolean close() {
        this.dbHelper.close();
        return !this.sqlDatabase.isOpen();
    }

    public boolean isOpen() {
        return this.sqlDatabase.isOpen();
    }

    public boolean databaseCreated() {
        return this.dbHelper.databaseCreated();
    }





    // 内部类
    private static class DbHelper extends SQLiteOpenHelper {
        private boolean databaseCreated = false;
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(APP_NAME, "Creating the application database");
            try {
                db.execSQL(
                        "CREATE VIRTUAL TABLE [" + TABLE_NAME + "] USING FTS3 (" +
                                "[" + KEY_USERNAME + "] TEXT," +
                                "[" + KEY_FULLNAME + "] TEXT," +
                                "[" + KEY_EMAIL + "] TEXT" + ");"
                );

                this.databaseCreated = true;
            } catch (Exception e) {
                Log.e(APP_NAME, "An error occurred while creating the database: " + e.toString(), e);
                this.deleteDatabaseStructure(db);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(APP_NAME, "Updating the database from the version " + oldVersion + "to" + newVersion + "...");
            this.deleteDatabaseStructure(db);
            this.onCreate(db);
        }

        public boolean databaseCreated() {
            return this.databaseCreated;
        }

        private boolean deleteDatabaseStructure(SQLiteDatabase db) {
            try {
                db.execSQL("DROP TABLE IF EXISTS [" + TABLE_NAME + "];");
                return true;
            } catch (Exception e) {
                Log.e(APP_NAME, "An error occurred while deleting the database: " + e.toString(), e);
            }
            return false;
        }
    }

}
