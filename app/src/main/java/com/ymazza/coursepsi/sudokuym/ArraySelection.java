package com.ymazza.coursepsi.sudokuym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArraySelection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_selection);
        TextView lvlselected = findViewById(R.id.lvlselected);

        LinearLayout listLayout=  findViewById(R.id.listLayout);
        ListView listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        String levellabel = intent.getExtras().getString("levellabel");
        int level = intent.getExtras().getInt("level");
        lvlselected.setText(levellabel);

        final ArrayList<vGrille> vGrilleList = new ArrayList<vGrille>();
        if (level == 1) {
            for (int i = 0; i < 100; i++) {
                vGrille vGrille = new vGrille(i, 1);
                vGrilleList.add(vGrille);

            }
        }else if (level == 2){
            for (int i = 0; i < 100; i++) {
                vGrille vGrille = new vGrille(i, 2);
                vGrilleList.add(vGrille);

            }
        }

        CustomListAdapter cla = new CustomListAdapter(this, vGrilleList);

        listView.setAdapter(cla);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentArraySelectionToGrille= new Intent( ArraySelection.this, GrilleActivity.class);
                Bundle b = new Bundle();
                b.putInt("idGrille", vGrilleList.get(i).getId());
                b.putInt("idDifficulte", vGrilleList.get(i).getLvl());
                intentArraySelectionToGrille.putExtras(b);
                startActivity(intentArraySelectionToGrille);
            }
        });

    }
}
