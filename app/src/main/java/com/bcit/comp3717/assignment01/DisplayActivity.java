package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

public class DisplayActivity extends AppCompatActivity {
    private String url = "https://s3.cointelegraph.com/storage/uploads/view/12bd78c00b2ee663f3ccb2657c4bc853.jpg";
    ImageView image;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent = getIntent();
        setArticleDisplay((Article) intent.getSerializableExtra("JSON_DOCUMENT"));
    }
    private void setArticleDisplay(Article article){
        image = findViewById(R.id.image);
        new DownloadImage().execute(article.getUrlToImage());

        TextView author = findViewById(R.id.author);
        TextView title = findViewById(R.id.title);
        TextView url = findViewById(R.id.url);
        TextView description = findViewById(R.id.description);
        TextView content = findViewById(R.id.content);
        TextView publishedAt = findViewById(R.id.publishedAt);

        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        url.setText(article.getUrl());
        content.setText(article.getContent());
        description.setText(article.getDescription());
        publishedAt.setText(article.getPublishedAt());
    }


    private class DownloadImage extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onProgressUpdate(Integer... progress) {
            progressBar.incrementProgressBy(progress[0]);
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}