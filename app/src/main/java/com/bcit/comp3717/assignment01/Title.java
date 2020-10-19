package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.ArrayList;

public class Title extends AppCompatActivity {

    //member attribute to store articles a news json object
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

    //concatenate a search keyword with the query
    private String buildQuery(String keyword) {
        return "https://newsapi.org/v2/everything?q=" + keyword + "&sortBy=publishedAt&apiKey=d756c14cccba4dad966144c75787dfa1";
    }

    //async method for http request
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

        //when the query is done, execute this
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ArticleAdapter adapter = new ArticleAdapter(Title.this, articleList);
            ListView titleLV = findViewById(R.id.newsList);;
            // Attach the adapter to a ListView
            titleLV.setAdapter(adapter);
            titleLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Article selectedItem = (Article) parent.getItemAtPosition(position);
                    Intent intent = new Intent(getBaseContext(), DisplayActivity.class);
                    Log.v("LOG", selectedItem.getDescription());
                    intent.putExtra("JSON_DOCUMENT", selectedItem);
                    startActivity(intent);
                }
            });
        }
    }
}
