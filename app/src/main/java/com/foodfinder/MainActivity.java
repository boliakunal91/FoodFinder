package com.foodfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<College> colleges;
    private CollegeListAdapter adapter;
    private ListView collegelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        colleges = new ArrayList<>();
        colleges.add(new College("Cal State Long Beach", 1, R.drawable.cal_state_long_beach, 90840, "long beach", "US", "CA",34.064645, -118.169612));
        colleges.add(new College("Cal State Los Angeles", 2, R.drawable.cal_state_los_angeles, 90032, "los angeles", "US", "CA", 34.064645, -118.169612));
        colleges.add(new College("Cal State Fullerton", 3, R.drawable.cal_state_fullerton, 92831,"los angeles", "US", "CA",33.8822926, -117.8853016));
        colleges.add(new College("Cal State Chico", 4, R.drawable.cal_state_chico, 95929,"chico", "US", "CA", 39.73029, -121.84785));
        colleges.add(new College("Cal State San Bernardino", 5, R.drawable.cal_state_san_bernardino, 92407,"san bernardino", "US", "CA", 34.1083449, -117.29));
        colleges.add(new College("Cal State Sacramento", 6, R.drawable.cal_state_sacramento, 95819,"sacramento", "US", "CA", 38.577695, -121.4278186));

        collegelist = (ListView) findViewById(R.id.list_restaurants);
        collegelist.setOnItemClickListener(this);
        adapter = new CollegeListAdapter(this, colleges);
        collegelist.setAdapter(adapter);

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
        if (id == R.id.search) {
            startActivity(new Intent(this, SearchBarActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       Intent intent = new Intent(this, YelpSearchActivity.class);
       intent.putExtra(StaticMembers.RESTAURANT_EXTRA, colleges.get(position));
        startActivity(intent);
        Toast.makeText(this, colleges.get(position).lattitude + "", Toast.LENGTH_LONG).show();
    }
}
