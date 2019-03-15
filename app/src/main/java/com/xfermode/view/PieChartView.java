package com.xfermode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xfermode.DisplayUtils;

/**
 * Created by wangzheng on 2019/3/12 9:01 AM.
 * E-mail : ivring11@163.com
 **/
public class PieChartView extends View {
    public static final int RADIUS = (int) DisplayUtils.dip2px(150);
    Paint paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds = new RectF();

    int[] angles = {60, 100, 120, 80};
    int[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.GRAY};

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight()/2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(colors[i]);
            canvas.drawArc(bounds, currentAngle, angles[i], true, paint);
            currentAngle += angles[i];
        }


    }
}
