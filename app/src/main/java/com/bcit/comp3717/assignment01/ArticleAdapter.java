package com.bcit.comp3717.assignment01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class  ArticleAdapter extends ArrayAdapter<Article> {
    Context _context;
    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context, 0, articles);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;

        // Get the data item for this position
        Article article = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_row_layout, parent, false);
        }

        // Lookup view for data population
        TextView textViewTitle = convertView.findViewById(R.id.title);

        // Populate the data into the template view using the data object
        textViewTitle.setText(String.format("%s", article.getTitle()));

        // Return the completed view to render on screen
        return convertView;
    }

}
