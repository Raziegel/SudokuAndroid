package com.ymazza.coursepsi.sudokuym;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;
import java.util.jar.Attributes;

/**
 * Created by Pro-MY on 25/04/2018.
 */

public class Draw extends View implements  View.OnTouchListener{

    private int coordX;
    private int coordY;
    private int selectedValue = 0;

    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int minSize = screenWidth < screenHeight ? screenWidth : screenHeight;

    private int pas = minSize / 9;

    private final int barre = 10;
    private final int correctionTextX = 20;
    private final int correctionTextY = 65;

    int[][] tableau = new int[9][9];

    public Draw(Context context, String chaine){
        super(context);
        this.setOnTouchListener(this);
        Log.i("sudoku", "");
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.tableau[i][j] = Character.getNumericValue(chaine.charAt((j)+9*(i)));
            }
        }

    }

    @Override
    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);

        paint.setTextSize(pas - 5);

        for (int i = 0; i <= 11; i++) {
            if (i % 3 ==0) {
                paint.setStrokeWidth(10);
                canvas.drawLine(0, i * pas, minSize, i * pas, paint);
                paint.setStrokeWidth(3);
            }else{
                canvas.drawLine(0, i * pas, minSize, i * pas, paint);
            }

            if (i < 10) {
                if (i % 3 ==0) {
                    paint.setStrokeWidth(10);
                    canvas.drawLine(i * pas, 0, i * pas, minSize, paint);
                    if (i <9) {
                        for (int j = 0; j < 9; j++) {
                            if (tableau[i][j] != 0) {
                                canvas.drawText(String.valueOf(tableau[i][j]), (j * pas) + 15, ((i + 1) * pas) - 10, paint);
                            }
                        }
                    }

                    paint.setStrokeWidth(3);
                }else{
                    canvas.drawLine(i * pas, 0, i * pas, minSize, paint);
                    if (i <9) {
                        for (int j = 0; j < 9; j++) {
                            if (tableau[i][j] != 0) {
                                canvas.drawText(String.valueOf(tableau[i][j]), (j * pas) + 15, ((i + 1) * pas) - 10, paint);
                            }
                        }
                    }
                }
            }
            //Draw grid Number
            canvas.drawLine(i * pas, barre * pas, i * pas, (barre + 1) * pas, paint);
            canvas.drawText(String.valueOf(i + 1), i * pas + correctionTextX, barre * pas + correctionTextY, paint);
        }


    }
    boolean firstTouch = false;
    long time = System.currentTimeMillis();
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        coordX = (int) motionEvent.getX();
        coordY = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (firstTouch && (System.currentTimeMillis() - time) <= 300) {
                    int index = getIndex(coordX, coordY);
                    if (index != -1)
                        //grilleAnswer = grilleAnswer.substring(0, index) + "0" + grilleAnswer.substring(index + 1);
                        Log.e("move","action_down");
                    firstTouch = false;

                } else {
                    firstTouch = true;
                    selectedValue = getSelectedNumber(coordX, coordY);
                    time = System.currentTimeMillis();
                }
                break;
            case MotionEvent.ACTION_UP:
                int index = getIndex(coordX, coordY);
                //if (index != -1 && grille.charAt(index) == '0' && selectedValue != 0) {
                 //   grilleAnswer = grilleAnswer.substring(0, index) + selectedValue + grilleAnswer.substring(index + 1);
                //}
                Log.e("move","action_down");
                selectedValue = 0;
                break;
        }
        this.invalidate();
        return true;
    }

    private int getIndex(int x, int y) {
        if (y > minSize)
            return -1;
        else
            return ((x / pas)) * 9 + (y / pas);

    }
    private int getSelectedNumber(int x, int y) {
        if (y < barre * pas + 1 || y > (barre + 1) * pas + 1)
            return 0;
        else
            return (x / pas) + 1;
    }
}
