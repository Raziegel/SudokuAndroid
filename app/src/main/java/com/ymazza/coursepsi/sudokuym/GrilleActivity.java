package com.ymazza.coursepsi.sudokuym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GrilleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
        Intent intent = this.getIntent();
        int level =  intent.getExtras().getInt("idGrille");
        Draw draw = new Draw(this);
        setContentView(draw);
    }
}
