package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.ArrayList;

public class Title extends AppCompatActivity {

    private ArrayList<Article> articleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        String query = buildQuery(keyword);

        new RetrieveNews().execute(query);
    }

//    private boolean isValid(String query) {
//        if(query.isEmpty() || query == null) {
//            return false;
//        } else if(query.) {
//
//        }
//    }

    private String buildQuery(String keyword) {
        return "https://newsapi.org/v2/everything?q=" + keyword + "&sortBy=publishedAt&apiKey=d756c14cccba4dad966144c75787dfa1";
    }

    private class RetrieveNews extends AsyncTask<String, Void, Void> {

        String TAG = Title.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... arg) {

            HttpHandler queryHandle = new HttpHandler();

            // Making a request to url and getting response
            String json = queryHandle.makeServiceCall(arg[0]);

            Log.e(TAG,  json);

            if(json != null) {
                Gson gson = new Gson();

                News news = gson.fromJson(json, News.class);

                articleList = news.getArticles();
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ArticleAdapter adapter = new ArticleAdapter(Title.this, articleList);
            ListView titleLV = findViewById(R.id.newsList);;
            // Attach the adapter to a ListView
            titleLV.setAdapter(adapter);
        }
    }
}
