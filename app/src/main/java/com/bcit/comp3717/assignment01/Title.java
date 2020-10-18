package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Title extends AppCompatActivity {

    private String TAG = Title.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        ArrayList<Article> articleList = new ArrayList<>();
        System.out.println(articleList);
        Log.v("JSON OUTPU", articleList.toString());
        ListView titleLV = findViewById(R.id.newsList);
        new GetContacts().execute();
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            // Making a request to url and getting response
            // URL to get contacts JSON
            String SERVICE_URL = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=d756c14cccba4dad966144c75787dfa1";
            jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG,  jsonStr);

            Gson gson = new Gson();

            News news = gson.fromJson(jsonStr, News.class);

            System.out.println(news);

            return null;
        }

//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//
//            NewsAdapter adapter = new NewsAdapter(Title.this, newsList);
//
//            // Attach the adapter to a ListView
//            titleLV.setAdapter(adapter);
//        }
    }
}