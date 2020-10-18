package com.bcit.comp3717.assignment01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendMsg();
    }

    protected void sendMsg() {

        final EditText searchBar = findViewById(R.id.editTextTextKeyword);

        Button btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchBar.getText().toString();
                Intent intent = new Intent(MainActivity.this, Title.class);
                intent.putExtra(EXTRA_MESSAGE, keyword);
                searchBar.setText("");
                startActivity(intent);
            }
        });
    }

}