package com.foodfinder;

import android.support.v7.app.AppCompatActivity;




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

public class YelpSearchActivity extends ListActivity {

	ArrayList<Business> businessObjs;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		setTitle("Searching...");
		Intent intent = getIntent();
		final College college = (College) intent.getSerializableExtra(StaticMembers.RESTAURANT_EXTRA);
		/*Intent intent = getIntent();
		final String searchTerm = intent.getData().getQueryParameter("term");
		final String searchLocation = intent.getData().getQueryParameter("location");*/

		setProgressBarIndeterminateVisibility(true);
		new AsyncTask<Void, Void, List<Business>>() {
			@Override
			protected List<Business> doInBackground(Void... params) {
				String businesses = Yelp.getYelp(YelpSearchActivity.this).search("restaurants", college.longitude, college.lattitude);
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
				getListView().setAdapter(new ArrayAdapter<Business>(YelpSearchActivity.this, android.R.layout.simple_list_item_1, businesses));
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
			Toast.makeText(YelpSearchActivity.this, "Null obj!!", Toast.LENGTH_SHORT).show();
		}


		Intent intent11 = new Intent(YelpSearchActivity.this, delete.class);
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







/*
import android.support.v7.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YelpSearchActivity extends AppCompatActivity {

	private TextView mSearchResultsText;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		final College college = (College) intent.getSerializableExtra(StaticMembers.RESTAURANT_EXTRA);
        setContentView(R.layout.main2);
        setTitle("Results...");
        mSearchResultsText = (TextView)findViewById(R.id.searchResults);

        setProgressBarIndeterminateVisibility(true);
        new AsyncTask<Void, Void, String>() {
        	@Override
        	protected String doInBackground(Void... params) {
                Yelp yelp = Yelp.getYelp(YelpSearchActivity.this);
                String businesses = yelp.search("restaurants", college.longitude, college.lattitude);
                try {
                	return processJson(businesses);
                } catch (JSONException e) {
                    return businesses;
                }
        	}

        	@Override
        	protected void onPostExecute(String result) {
        		mSearchResultsText.setText(result);
        		setProgressBarIndeterminateVisibility(false);
        	}
        }.execute();
    }

	String processJson(String jsonStuff) throws JSONException {
		JSONObject json = new JSONObject(jsonStuff);
		JSONArray businesses = json.getJSONArray("businesses");
		ArrayList<String> businessNames = new ArrayList<String>(businesses.length());
		for (int i = 0; i < businesses.length(); i++) {
			JSONObject business = businesses.getJSONObject(i);
			businessNames.add(business.getString("name"));
		}
		return TextUtils.join("\n", businessNames);
	}
} */