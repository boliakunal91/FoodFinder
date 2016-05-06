package com.foodfinder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SearchBarActivity extends AppCompatActivity {

	private EditText mSearchTerm;
	private EditText mSearchLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);

		setTitle("Find Me Some Food!!");
		mSearchTerm = (EditText)findViewById(R.id.searchTerm);
		mSearchLocation = (EditText)findViewById(R.id.searchLocation);
	}

	public void search(View v) {
		String term = mSearchTerm.getText().toString();
		String location = mSearchLocation.getText().toString();
		Intent intent = new Intent(this, YelpSearchListActivity.class);
		intent.setData(new Uri.Builder().appendQueryParameter("term", term).appendQueryParameter("location", location).build());
		startActivity(intent);
	}


}
