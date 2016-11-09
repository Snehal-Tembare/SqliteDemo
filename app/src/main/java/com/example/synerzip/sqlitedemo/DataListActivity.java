package com.example.synerzip.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by synerzip on 17/3/16.
 */
public class DataListActivity extends Activity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    List listContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_layout);

        listView=(ListView)findViewById(R.id.idListView);

        listContacts=new ArrayList();



        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase(MainActivity.PASSWORD);

        cursor=userDbHelper.getInformations(sqLiteDatabase);

        if(cursor.moveToFirst())
        {
            int id;
            String name,mo,email;
            do {
                id=cursor.getInt(0);
                name=cursor.getString(1);
                mo=cursor.getString(2);
                email=cursor.getString(3);

                DataProvider dataProvider=new DataProvider(id,name,mo,email);
                listContacts.add(dataProvider);
                //listDataAdapter.add(dataProvider);

            }while (cursor.moveToNext());
        }
        listDataAdapter=new ListDataAdapter(getApplicationContext(),R.layout.row_layout,listContacts);
        listView.setAdapter(listDataAdapter);

    }
}
