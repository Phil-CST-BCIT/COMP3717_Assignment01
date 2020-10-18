package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.InputStream;

/**
 * This class represents a display activity, which is passed a JSON document
 * as an intent, finally displaying the information on the page.
 */

public class DisplayActivity extends AppCompatActivity {

    ImageView image;
    ProgressBar progressBar;

    /**
     * Creation method for activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_name);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Intent intent = getIntent();
        Article article = (Article) intent.getSerializableExtra("JSON_DOCUMENT");
        if (article != null) {
            setArticleDisplay(article);
        }
    }

    /**
     * Sets the TextViews of the article display activity.
     * @param article containing data to display.
     */
    private void setArticleDisplay(Article article){
        image = findViewById(R.id.image);
        new DownloadImage().execute(article.getUrlToImage());

        TextView author = findViewById(R.id.author);
        TextView title = findViewById(R.id.title);
        TextView url = findViewById(R.id.url);
        TextView description = findViewById(R.id.description);
        TextView content = findViewById(R.id.content);
        content.setMovementMethod(new ScrollingMovementMethod());
        TextView publishedAt = findViewById(R.id.publishedAt);
        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        url.setText(article.getUrl());
        content.setText(article.getContent());
        description.setText(article.getDescription());
        publishedAt.setText(article.getPublishedAt());
    }

    /**
     * Downloads image from given URL and displays in an ImageView
     */
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