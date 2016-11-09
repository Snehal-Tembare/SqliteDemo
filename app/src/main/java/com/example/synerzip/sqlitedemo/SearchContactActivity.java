package com.example.synerzip.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by synerzip on 18/3/16.
 */
public class SearchContactActivity extends Activity {
    TextView Display_mobile;
    TextView Display_email;
    TextView Display_Name;
    List contact_list;
    ListView listView;
    ListDataAdapter listDataAdapter;
    DataProvider dataProvider;
    int delete_id;
    Button dltButton;


    EditText search_name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    String searchname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact_layout);

        search_name=(EditText)findViewById(R.id.search_ID);

        listView=(ListView)findViewById(R.id.searchlist);

        listView.setVisibility(View.GONE);
        dltButton=(Button)findViewById(R.id.delete);
        contact_list=new ArrayList();

    }

    public void search_Contact(View view)
    {
        //searchID=Integer.parseInt(Search_ID.getText().toString());
        searchname=search_name.getText().toString();
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase(MainActivity.PASSWORD);
        Cursor cursor=userDbHelper.getContact(searchname);

        //cursor=userDbHelper.getInformations(sqLiteDatabase);


        if(cursor.getCount()>0){
            listView.setVisibility(View.VISIBLE);
        }
        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String NAME = cursor.getString(1);
                String MOBILE = cursor.getString(2);
                String EMAIL = cursor.getString(3);

                DataProvider dataProvider = new DataProvider(id, NAME, MOBILE, EMAIL);
                contact_list.add(dataProvider);
            }while (cursor.moveToNext());
        }
        listDataAdapter=new ListDataAdapter(getApplicationContext(),R.layout.row_layout,contact_list);
        listView.setAdapter(listDataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataProvider=(DataProvider)contact_list.get(position);
                delete_id=dataProvider.getId();
                userDbHelper.deleteInformation(delete_id);
                Toast.makeText(getBaseContext(),"Contact Deleted...",Toast.LENGTH_LONG).show();

            }
        });

    }

//public void update_Contact(View view)
//{
//
//}
//
//    public void deleteContact(View view)
//    {
//        //int ID=Integer.parseInt(Search_ID.getText().toString());
//        userDbHelper=new UserDbHelper(getApplicationContext());
//        sqLiteDatabase=userDbHelper.getReadableDatabase();
//        userDbHelper.deleteInformation(ID);
//        Toast.makeText(getBaseContext(),"Contact Deleted...",Toast.LENGTH_LONG).show();
//
//    }


}
