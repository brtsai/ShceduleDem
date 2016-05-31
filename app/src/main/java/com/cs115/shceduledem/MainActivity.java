package com.cs115.shceduledem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String xlsfile = intent.getStringExtra("xlsfile");
        Log.d("Test1", "XLSFILE IS:"+xlsfile);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ScheduleDisplayFragment sdf = new ScheduleDisplayFragment();

            Bundle bundle = new Bundle();
            bundle.putString("xlsfile", xlsfile);
            sdf.setArguments(bundle);

            ft.add(R.id.fragment_container, sdf);
            ft.commit();
        }
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
        if (id == R.id.action_help) {
            //Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            //startActivity(intent);
            return true;
        }

        if (id == R.id.action_switchadmin) {
            Intent current_intent = getIntent();
            String xlsfile = current_intent.getStringExtra("xlsfile");

            if(xlsfile!=null) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                intent.putExtra("xlsfile", xlsfile);
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
