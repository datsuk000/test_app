package com.example.roman.test_app.utilities;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHelper {
    private static final String TAG = "HttpHelper";
    private static final String REQUEST_METHOD_GET = "GET";

    public static String downloadData(String urlAddress) throws IOException {
        Log.i(TAG, "Starting downloading data from url");
        if(TextUtils.isEmpty(urlAddress)) {
            return null;
        }
        URL url = new URL(urlAddress);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(REQUEST_METHOD_GET);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }


        Log.i(TAG, "Finished downloading data ");

        return result.toString();
    }
}
