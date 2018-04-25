package com.ymazza.coursepsi.sudokuym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pro-MY on 25/04/2018.
 */

public class CustomListAdapter extends ArrayAdapter<vGrille> {
    public CustomListAdapter(Context context, ArrayList<vGrille> vGrilleArrayList) {
        super(context, 0, vGrilleArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        vGrille vGrille = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvInfo = (TextView) convertView.findViewById(R.id.tvInfo);
        TextView tvCompletion = (TextView) convertView.findViewById(R.id.tvCompletion);
        // Populate the data into the template view using the data object
        tvInfo.setText("Difficulté : " + String.valueOf(vGrille.getLvl()) + " : Grille " + String.valueOf(vGrille.getId()));
        tvCompletion.setText(String.valueOf(vGrille.getDone()));
        if (vGrille.getDone() > 40){
            tvCompletion.setTextColor(0xFF00FF00);
        }else{
            tvCompletion.setTextColor(0xFFFF0000);
        }



        // Return the completed view to render on screen
        return convertView;
    }

}