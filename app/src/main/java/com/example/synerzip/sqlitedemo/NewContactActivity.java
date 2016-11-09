package com.example.synerzip.sqlitedemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by synerzip on 17/3/16.
 */
public class NewContactActivity extends Activity {
    EditText Contactname,Contactmobile,Contactemail;
    Context context=this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_layout);
        Contactname=(EditText)findViewById(R.id.contact_name);
        Contactmobile=(EditText)findViewById(R.id.contact_mobile);
        Contactemail=(EditText)findViewById(R.id.contact_email);

    }


    public void addContact(View v)
    {
        String name=Contactname.getText().toString();
        String mob=Contactmobile.getText().toString();
        String email=Contactemail.getText().toString();

        userDbHelper=new UserDbHelper(context);
        sqLiteDatabase=userDbHelper.getWritableDatabase(MainActivity.PASSWORD);

        userDbHelper.addInformation(name,mob,email,sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Data saved",Toast.LENGTH_LONG).show();
        userDbHelper.close();        startActivity(new Intent(this,MainActivity.class));


    }
}
