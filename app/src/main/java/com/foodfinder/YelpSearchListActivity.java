package com.foodfinder;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YelpSearchListActivity extends ListActivity {

	ArrayList<Business> businessObjs;


	@Override
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setTitle("Searching...");

        Intent intent = getIntent();
        final String searchTerm = intent.getData().getQueryParameter("term");
        final String searchLocation = intent.getData().getQueryParameter("location");

        setProgressBarIndeterminateVisibility(true);
        new AsyncTask<Void, Void, List<Business>>() {
        	@Override
        	protected List<Business> doInBackground(Void... params) {
                String businesses = Yelp.getYelp(YelpSearchListActivity.this).search(searchTerm, searchLocation);
                try {
                	return processJson(businesses);
                } catch (JSONException e) {
                    return Collections.<Business>emptyList();
                }
        	}

        	@Override
        	protected void onPostExecute(List<Business> businesses) {
        		setTitle("Tacos Found");
        		setProgressBarIndeterminateVisibility(false);
        		getListView().setAdapter(new ArrayAdapter<Business>(YelpSearchListActivity.this, android.R.layout.simple_list_item_1, businesses));
        	}
        }.execute();
    }

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		Business biz = (Business)listView.getItemAtPosition(position);
		//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(biz.url)));
		//String delstring = biz.name;
		//System.out.print(biz.name);
		if(businessObjs.get(position)== null)
		{
			Toast.makeText(YelpSearchListActivity.this, "Null obj!!", Toast.LENGTH_SHORT).show();
		}


		Intent intent11 = new Intent(YelpSearchListActivity.this, delete.class);
		intent11.putExtra("test", businessObjs.get(position));

		startActivity(intent11);
	}

	List<Business> processJson(String jsonStuff) throws JSONException {
		JSONObject json = new JSONObject(jsonStuff);
		JSONArray businesses = json.getJSONArray("businesses");
		//ArrayList<Business>
				businessObjs = new ArrayList<Business>(businesses.length());
		for (int i = 0; i < businesses.length(); i++) {
			JSONObject business = businesses.getJSONObject(i);
            JSONObject json_location = business.getJSONObject("location");
            JSONObject json_coordinate = json_location.getJSONObject("coordinate");
            double lat = json_coordinate.optDouble("latitude");
            double longitude = json_coordinate.optDouble("longitude");
            JSONArray address = json_location.getJSONArray("display_address");
            String postal = json_location.optString("postal_code");
            //String add1 = address.getString(0);
            //String add2 = address.getString(1);
           // String add3 = address.getString(2);
           // String address_full = add1 + add2 + add3;
            String address_full = "temp address";
          // if(business.has("reservation_url"))
               // {
                    String reserve_url = business.optString("reservation_url");
                    businessObjs.add(new Business(business.optString("name"), business.optString("mobile_url"), business.optString("display_phone"), lat, longitude, address_full, postal /*,business.optString("0")*/));
               // }else {
             //  String reserve_url = "no url";
              // businessObjs.add(new Business(business.optString("name"), business.optString("mobile_url"), business.optString("display_phone"), lat, longitude, address_full, reserve_url /*,business.optString("0")*/));
          // }
         //   JSONObject zero = json_addr.getJSONObject(0);
			//businessObjs.add(new Business(business.optString("name"), business.optString("mobile_url"), business.optString("display_phone"), lat, longitude, address_full /*,business.optString("0")*/));

        }
		return businessObjs;
	}
}
