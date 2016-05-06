package com.foodfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class delete extends AppCompatActivity {

    TextView rest_name, rest_phone, rest_latlong, rest_address ;
    TextView price ;
    TextView address ;
    TextView call ;
    private String tempurl;

    private static String url = "http://opentable.herokuapp.com/api/restaurants?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        //Bundle b = intent.getExtras();
        //System.out.print((String) b.get("test"));
        Business business = (Business)intent.getSerializableExtra("test");
        double latitude = business.latitude;
        double longitude = business.longitude;
        //String s = intent.getStringExtra("test");
       rest_name = (TextView) findViewById(R.id.textView_rest_name);
      //System.out.print(business.name );
       rest_name.setText(business.name);
        rest_phone = (TextView) findViewById(R.id.textview_call);
        rest_phone.setText(business.phone);
        final String tel_number = rest_phone.getText().toString();
        rest_latlong = (TextView) findViewById(R.id.textview_get_directions);
       //rest_latlong.setText(Double.toString(business.latitude));
        //rest_latlong.setText(business.city);
        rest_address = (TextView) findViewById(R.id.textview_address);
        rest_address.setText(business.postal);

        final String lat = String.valueOf(latitude);
        final String longi = String.valueOf(longitude);

        rest_latlong.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+ lat + longi));
                startActivity(intent);

            }
        });


        rest_phone.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String url = "tel:"+ tel_number;
                Toast.makeText(delete.this, url, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                //startActivity(intent);
                try {
                    startActivity(intent);
                } catch (SecurityException e) {

                }
            }
        });
        url += "country=US" + "&name=" +  business.name + "&zip=" + business.postal;// + "&postal_code=" + college.zipCode;
        url = url.replace(" ", "%20");

        //JSONObject OpenTableObj =  new JSONparse().execute();
        new JSONparse().execute();

        //Toast.makeText(delete.this, tempurl, Toast.LENGTH_SHORT).show();
    }

    public void reservation(View view){

        Intent intent = new Intent(delete.this, ReservationActivity.class);
        intent.putExtra("url_res", tempurl);
        startActivity(intent);
    }
    private class JSONparse extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            JSONParser jParser = new JSONParser();
            JSONArray json = new JSONArray();
            // Getting JSON from URL
            try {
                json = jParser.getJSONFromUrl(url).getJSONObject("restaurants").getJSONArray("0");
            }
            catch(Exception e){

            }
            Log.e("myapp", json.toString());
//            tempurl = json.optString("mobile_reserve_url");
            return json;
        }



      /*  @Override
        protected void onPostExecute(JSONObject json){



        }*/
    }

/*
    private class JSONParse extends AsyncTask<String, String, JSONObject> {


        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);

            return json;
        }

       @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array from URL
                Log.e("test", json.toString());
                android = json.getJSONArray(TAG_RESTAURANTS);
                for (int i = 0; i < android.length(); i++) {
                    JSONObject c = android.getJSONObject(i);

                    restaurants = new ArrayList<>();

                    // Storing  JSON item in a Variable
                    String price = c.getString(TAG_PRICE);
                    String name = c.getString(TAG_NAME);
                    String phone = c.getString(TAG_PHONE);
                    String address = c.getString(TAG_ADDRESS);
                    String city = c.getString(TAG_CITY);
                    String state = c.getString(TAG_STATE);
                    String postal_code = c.getString(TAG_POSTAL_CODE);
                    // Adding value HashMap key => value

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } */


    }


   /* private class JSONparse extends AsyncTask<String, String, JSONObject>{



        @Override
        protected JSONObject doInBackground(String... params) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }


    } */
