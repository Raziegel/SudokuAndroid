package com.ymazza.coursepsi.sudokuym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "lvl0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button lvl1 = findViewById(R.id.lvl1);

        lvl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


                //Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                //intent.putExtra(SearchManager.QUERY, String.valueOf(t.getText()));

                Intent intentMainToActivity = new Intent( MainActivity.this, ArraySelection.class);
                intentMainToActivity.putExtra(EXTRA_MESSAGE,"Level 1");
                startActivity(intentMainToActivity);

            }
        });

        Button lvl2 = findViewById(R.id.lvl2);

        lvl2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


                //Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                //intent.putExtra(SearchManager.QUERY, String.valueOf(t.getText()));

                Intent intentMainToActivity = new Intent( MainActivity.this, ArraySelection.class);
                intentMainToActivity.putExtra(EXTRA_MESSAGE,"Level 2");
                startActivity(intentMainToActivity);

            }
        });

    }
}
