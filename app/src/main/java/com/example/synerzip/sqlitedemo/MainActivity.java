package com.example.synerzip.sqlitedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;

public class MainActivity extends Activity implements View.OnClickListener {
    public static String PASSWORD;
    Button btnAddCon,btnViewCon,btnSearchCon,btnUpdateCon,btnDeleteCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);SQLiteDatabase.loadLibs(this);
        PASSWORD=Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        btnAddCon=(Button)findViewById(R.id.idAddCon);
        btnAddCon.setOnClickListener(this);

        btnViewCon=(Button)findViewById(R.id.idViewCon);
        btnViewCon.setOnClickListener(this);

        btnSearchCon=(Button)findViewById(R.id.idSearchCon);
        btnSearchCon.setOnClickListener(this);

        btnUpdateCon=(Button)findViewById(R.id.idUpdateCon);
        btnUpdateCon.setOnClickListener(this);

        btnDeleteCon=(Button)findViewById(R.id.idDeleteCon);
        btnDeleteCon.setOnClickListener(this);

        Log.i("*********ID", PASSWORD);

    }





    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.idAddCon:
                Intent intent=new Intent(this,NewContactActivity.class);
                startActivity(intent);
                break;

            case R.id.idViewCon:
               Intent intent1=new Intent(this,DataListActivity.class);
                startActivity(intent1);
                      break;
            case R.id.idSearchCon:
                Intent intent2=new Intent(this,SearchContactActivity.class);
                startActivity(intent2);
                break;
            case R.id.idDeleteCon:
                Intent intent3=new Intent(this,SearchContactActivity.class);
                startActivity(intent3);
                break;
            case R.id.idUpdateCon:
                Intent intent4=new Intent(this,UpdateContactActivity.class);
                startActivity(intent4);
                break;
            default:
                Toast.makeText(getBaseContext(),"Wrong Choice...",Toast.LENGTH_LONG).show();


        }

    }
}
