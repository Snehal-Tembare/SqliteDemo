package com.example.synerzip.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * Created by synerzip on 17/3/16.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    public Context mContext;
    private static final String DATABASE_NAME="USERINFO.db";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY="CREATE TABLE " + UserContact.TABLE_NAME+"("+ UserContact.USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+UserContact.USER_NAME+" TEXT, "+UserContact.USER_MOB+" TEXT,"+UserContact.USER_EMAIL+" TEXT);" ;

    public UserDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //mContext=context;
        Log.e("DATABASE_OPERATIONS", "Database created/opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE_OPERATIONS","Table Created...");

        }

    public void addInformation(String name,String mob,String  email,SQLiteDatabase db){

        ContentValues contentValues=new ContentValues();

        contentValues.put(UserContact.USER_NAME,name);
        contentValues.put(UserContact.USER_MOB,mob);
        contentValues.put(UserContact.USER_EMAIL,email);

        db.insert(UserContact.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATION","One row inserted...");
   }

    public Cursor getInformations(SQLiteDatabase db)
    {
        Cursor cursor;
        String[] projections={UserContact.USER_ID,UserContact.USER_NAME,UserContact.USER_MOB,UserContact.USER_EMAIL};
        cursor=db.query(UserContact.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }



    public Cursor getContact(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase(MainActivity.PASSWORD);
        Cursor res =  db.rawQuery("select * from user_info where "+ UserContact.USER_NAME+"='"+name+"'" , null);
        return res;
    }



    public Integer deleteInformation (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase(MainActivity.PASSWORD);
        return db.delete("user_info","id = ? ",new String[] { Integer.toString(id) });
    }





    public boolean updateInformation (Integer id, String name, String mob, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase(MainActivity.PASSWORD);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", name);
        contentValues.put("user_mob", mob);
        contentValues.put("user_email", email);
        db.update("user_info", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ UserContact.TABLE_NAME);

    }
}
