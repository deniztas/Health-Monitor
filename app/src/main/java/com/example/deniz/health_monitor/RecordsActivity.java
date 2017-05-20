package com.example.deniz.health_monitor;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deniz on 19.05.2017.
 */

public class RecordsActivity extends AppCompatActivity {

    private TextView alert;
    private ListView activity_list;
    ArrayList<String> listItems=new ArrayList<String>();
    private ArrayAdapter<String> adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        alert = (TextView)findViewById(R.id.textView);
        activity_list = (ListView) findViewById(R.id.listView);
        ListActivities ();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.homapage){
            Intent intent = new Intent(RecordsActivity.this,MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.values){
            Intent intent = new Intent(RecordsActivity.this,ValuesActivity.class);
            startActivity(intent);
        }
        if (id == R.id.exit) {
            finishAffinity();
        }


        return super.onOptionsItemSelected(item);
    }

    //Read file and list on list view
    public void ListActivities () {
            String filename= "/storage/emulated/0/Notes/Activities.txt";


        BufferedReader reader = null;
        try {


            InputStream input = new FileInputStream(filename);
            reader = new BufferedReader(new InputStreamReader(input));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                listItems.add((mLine));
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
            if(listItems.isEmpty()){
                alert.setText("YOU HAVE NO RECORD");
            }
            else{
                alert.setText("YOUR ACTIVITY RECORDS");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
                activity_list.setAdapter(adapter);
            }


    }
}


