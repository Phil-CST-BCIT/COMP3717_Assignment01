package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;

public class DisplayActivity extends AppCompatActivity {
    private String url = "https://s3.cointelegraph.com/storage/uploads/view/12bd78c00b2ee663f3ccb2657c4bc853.jpg";
    ImageView image;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        image = findViewById(R.id.image);
        new DownloadImage().execute(url);
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