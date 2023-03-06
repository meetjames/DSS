package com.example.dss;

//public class UserDatabase {
//}




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DSS03_DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Ddss_hodal";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "dss03_member_details";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_ADDRESS = "address";

    public DSS03_DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_AGE + " INTEGER,"
                + KEY_GENDER + " TEXT,"
                + KEY_ADDRESS + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(DSS03_declare user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_ADDRESS, user.getAddress());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

//    public ArrayList<DSS03_declare> getAllUsers() {
//        ArrayList<DSS03_declare> userList = new ArrayList<>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//        if (cursor.moveToFirst()) {
//            do {
//                DSS03_declare user = new DSS03_declare();
//                user.setId(Integer.parseInt(cursor.getString(0)));
//                user.setName(cursor.getString(1));
//                user.setAge(Integer.parseInt(cursor.getString(2)));
//                user.setGender(cursor.getString(3));
//                user.setAddress(cursor.getString(4));
//                userList.add(user);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return userList;
//    }
}
