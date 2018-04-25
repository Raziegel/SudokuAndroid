package com.ymazza.coursepsi.sudokuym;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
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

    private int minSize;
    private int stepSize = minSize / 9;


    public Draw(Context context){
        super(context);
        if(screenWidth < screenHeight){
            this.minSize=screenWidth;
        }else {
            this.minSize = screenHeight;
        }
        this.setOnTouchListener(this);
    }
    public void OnDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setTextSize(stepSize - 5);

        for (int i = 0; i <= 11; i++) {
            canvas.drawLine(0, i * stepSize, minSize, i * stepSize, paint);
            if (i < 10)
                canvas.drawLine(i * stepSize, 0, i * stepSize, minSize, paint);

            //Draw grid Number
            canvas.drawLine(i * stepSize, 10 * stepSize, i * stepSize, (10 + 1) * stepSize, paint);
            canvas.drawText(String.valueOf(i + 1), i * stepSize + 35, 10 * stepSize + 140, paint);
        }


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
