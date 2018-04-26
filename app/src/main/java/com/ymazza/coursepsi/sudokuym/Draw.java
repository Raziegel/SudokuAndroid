package com.ymazza.coursepsi.sudokuym;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;
import android.widget.Toast;

import java.util.Arrays;
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

    int[][] tableaureponse = new int[9][9];
    int[][] enigme = new int[9][9];

    public Draw(Context context, String chaine){
        super(context);
        this.setOnTouchListener(this);
        Log.i("sudoku", "");
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.tableaureponse[i][j] = Character.getNumericValue(chaine.charAt((j)+9*(i)));
                this.enigme[i][j] = Character.getNumericValue(chaine.charAt((j)+9*(i)));
            }
        }

    }

    @Override
    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);

        paint.setTextSize(pas - 5);
        if (selectedValue !=0) {
            canvas.drawText(String.valueOf(selectedValue), coordX, coordY, paint);
        }

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
                            if (tableaureponse[i][j] != 0) {
                                if (enigme[i][j] == 0){
                                    paint.setColor(Color.GRAY);
                                    canvas.drawText(String.valueOf(tableaureponse[i][j]), (j * pas) + 15, ((i + 1) * pas) - 10, paint);
                                    paint.setColor(Color.BLACK);
                                }else {
                                    canvas.drawText(String.valueOf(tableaureponse[i][j]), (j * pas) + 15, ((i + 1) * pas) - 10, paint);
                                }
                            }
                        }
                    }

                    paint.setStrokeWidth(3);
                }else{
                    canvas.drawLine(i * pas, 0, i * pas, minSize, paint);
                    if (i <9) {
                        for (int j = 0; j < 9; j++) {
                            if (tableaureponse[i][j] != 0) {
                                if (enigme[i][j] == 0){
                                    paint.setColor(Color.GRAY);
                                    canvas.drawText(String.valueOf(tableaureponse[i][j]), (j * pas) + 15, ((i + 1) * pas) - 10, paint);
                                    paint.setColor(Color.BLACK);
                                }else {
                                    canvas.drawText(String.valueOf(tableaureponse[i][j]), (j * pas) + 15, ((i + 1) * pas) - 10, paint);
                                }

                            }
                        }
                    }
                }
            }
            //Draw grid Number
            canvas.drawLine(i * pas, barre * pas, i * pas, (barre + 1) * pas, paint);
            canvas.drawText(String.valueOf(i + 1), i * pas + correctionTextX, barre * pas + correctionTextY, paint);
            if (checkSudokuStatus(tableaureponse) && selectedValue==0){
                Context context = this.getContext();
                CharSequence text = "Win!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
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
                int xcase = ((int) (coordX)/pas);

                int ycase = ((int) (coordY)/pas);
                if ( ycase <= 8 && xcase <= 8  && selectedValue != 0) {
                    if (enigme[ycase][xcase] == 0) {
                        Log.e("move", "move ok selectedValue:" + String.valueOf(selectedValue));

                        tableaureponse[ycase][xcase] = selectedValue;
                    }
                }
                Log.e("move","action_up");
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
    private boolean checkSudokuStatus(int[][] grid) {
        for (int i = 0; i < 9; i++) {

            int[] row = new int[9];
            int[] square = new int[9];
            int[] column = grid[i].clone();

            for (int j = 0; j < 9; j ++) {
                row[j] = grid[j][i];
                square[j] = grid[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            if (!(validate(column) && validate(row) && validate(square)))
                return false;
        }
        return true;
    }

    private boolean validate(int[] check) {
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                return false;
        }
        return true;
    }

}
