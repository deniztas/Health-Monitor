package com.example.deniz.health_monitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by deniz on 19.05.2017.
 */

public class ValuesActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);

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
            Intent intent = new Intent(ValuesActivity.this,MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.records){
            Intent intent = new Intent(ValuesActivity.this,RecordsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.exit) {
            finishAffinity();
        }


        return super.onOptionsItemSelected(item);
    }


}
