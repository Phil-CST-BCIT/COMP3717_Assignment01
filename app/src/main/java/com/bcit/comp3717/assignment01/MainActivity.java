package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "keyword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchBar = (EditText) findViewById(R.id.editTextTextKeyword);
        final String keyword = searchBar.getText().toString();

        Button btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(MainActivity.this, Title.class);
            @Override
            public void onClick(View v) {
                intent.putExtra(EXTRA_MESSAGE, keyword);
                startActivity(intent);
            }
        });

        searchBar.setText("");
    }

}