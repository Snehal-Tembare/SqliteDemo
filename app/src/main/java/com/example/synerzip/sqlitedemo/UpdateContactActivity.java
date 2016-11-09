package com.example.synerzip.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by synerzip on 18/3/16.
 */
public class UpdateContactActivity extends Activity {
    EditText Name_search,New_mobile,New_email,New_name;
    TextView titletext;
    String Searchname,NewName,NewMobile,NewEmail;
    int New_ID,search_ID;

    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Button Updatebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact_layout);
        Name_search=(EditText)findViewById(R.id.name_search);
        New_name=(EditText)findViewById(R.id.new_name);
        New_mobile=(EditText)findViewById(R.id.new_mob);
        New_email=(EditText)findViewById(R.id.new_email);
        Updatebutton=(Button)findViewById(R.id.updateButton);
        titletext=(TextView)findViewById(R.id.title_text);
        titletext.setVisibility(View.GONE);
        New_name.setVisibility(View.GONE);
        New_mobile.setVisibility(View.GONE);
        New_email.setVisibility(View.GONE);
        Updatebutton.setVisibility(View.GONE);


    }
    public void searchContact(View view)
    {
        Searchname=Name_search.getText().toString();
        //search_ID=Name_search.getId();
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase(MainActivity.PASSWORD);
        Cursor cursor=userDbHelper.getContact(Searchname);
        if(cursor.moveToFirst())
        {

            New_ID=cursor.getInt(0);
            NewName=cursor.getString(1);
            NewMobile=cursor.getString(2);
            NewEmail=cursor.getString(3);
//            NewName=Searchname;

            New_name.setText(NewName);
            New_mobile.setText(NewMobile);
            New_email.setText(NewEmail);

            titletext.setVisibility(View.VISIBLE);
            New_name.setVisibility(View.VISIBLE);
            New_mobile.setVisibility(View.VISIBLE);
            New_email.setVisibility(View.VISIBLE);
            Updatebutton.setVisibility(View.VISIBLE);
        }

    }
    public void updateContact(View view)
    {
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getWritableDatabase(MainActivity.PASSWORD);
        String name,mob,email;

        name=New_name.getText().toString();
        email=New_email.getText().toString();
        mob=New_mobile.getText().toString();

        boolean count=userDbHelper.updateInformation(search_ID,name,mob,email);
        if(count==true) {
            Toast.makeText(getBaseContext(), " rows updated ", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getBaseContext(), " NOt updated ", Toast.LENGTH_LONG).show();

        finish();


    }
}
