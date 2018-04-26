package com.ymazza.coursepsi.sudokuym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GrilleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        int level =  intent.getExtras().getInt("idDifficulte");
        Draw draw = new Draw(this, "234956817957814263186372459549681732618723594723495681392567148475138926861249370");
        if (level ==1) {
             draw = new Draw(this, "234956817957814263186372459549681732618723594723495681392567148475138926861249370");
        }else if (level ==2){
             draw = new Draw(this,"008203500009670408346050702430010059967005001000496203280034067703500904004107020");
        }
        setContentView(draw);
    }
}
