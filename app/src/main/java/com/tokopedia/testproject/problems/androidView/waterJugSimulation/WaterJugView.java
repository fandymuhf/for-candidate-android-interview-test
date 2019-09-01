package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class WaterJugView extends View {

    private int maxWater = 0;
    private int waterFill = 0;
    private Paint circlePaint;

    public WaterJugView(Context context) {
        super(context);
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WaterJugView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMaxWater(int maxWater) {
        this.maxWater = maxWater;
    }

    public void setWaterFill(int waterFill) {
        this.waterFill = waterFill;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);

        //draw water
        int precentageairPinggir = (25*waterFill/maxWater);
        Path wallpath = new Path();
        wallpath.reset(); // only needed when reusing this path for a new build
        wallpath.moveTo(getWidth()/2-50-precentageairPinggir, getHeight()-(getHeight()/2)*waterFill/maxWater);
        wallpath.lineTo(getWidth()/2-50, getHeight());
        wallpath.lineTo(getWidth()/2+50, getHeight());
        wallpath.lineTo(getWidth()/2+50+precentageairPinggir, getHeight()-(getHeight()/2)*waterFill/maxWater);

        canvas.drawPath(wallpath, paint);

        //draw glass
        paint.setStrokeWidth(8);
        paint.setColor(Color.BLACK);

        canvas.drawLine(getWidth()/2-75,getHeight()/2,getWidth()/2-50,getHeight(),paint);
        canvas.drawLine(getWidth()/2-50,getHeight(),getWidth()/2+50,getHeight(),paint);
        canvas.drawLine(getWidth()/2+75,getHeight()/2,getWidth()/2+50,getHeight(),paint);

    }

    //TODO
    /*
    Based on these variables: maxWater and waterFill, draw the jug with the water

    Example a:
    maxWater = 10
    waterFill = 0

    Result,
    View will draw like below
    |        |
    |        |
    |        |
    |        |
    `--------'

    Example b:
    maxWater = 10
    waterFill = 5

    Result,
    View will draw like below
    |        |
    |        |
    |--------|
    |        |
    `--------'

    Example c:
    maxWater = 10
    waterFill = 8

    Result,
    View will draw like below
    |        |
    |--------|
    |        |
    |        |
    `--------'

    Example d:
    maxWater = 10
    waterFill = 10

    Result,
    View will draw like below
     ________
    |        |
    |        |
    |        |
    |        |
    `--------'
    */

}
