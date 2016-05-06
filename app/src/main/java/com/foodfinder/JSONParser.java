package com.foodfinder;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.InputStream;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {
    }

    public JSONObject getJSONFromUrl(String url) {

        jObj = new JSONObject();

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            Log.e("test", url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String apiResponse = EntityUtils.toString(httpEntity);
            jObj = new JSONObject(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return JSON String
        return jObj;
    }
}