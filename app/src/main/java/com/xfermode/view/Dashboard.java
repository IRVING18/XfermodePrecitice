package com.xfermode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xfermode.DisplayUtils;

/**
 * Created by wangzheng on 2019/3/11 7:11 PM.
 * E-mail : ivring11@163.com
 **/
public class Dashboard extends View {
    public static final int   ANGLE  = 120;
    public static final float RADIUS = DisplayUtils.dip2px(150);
    public static final float LENGTH = DisplayUtils.dip2px(100);
    Paint paint         = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path  dashShapePath = new Path();
    float dashWidth     = DisplayUtils.dip2px(2);
    private PathDashPathEffect dashPathEffect;


    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * 代码块在构造方法后执行
     */ {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DisplayUtils.dip2px(2));

        //刻度块,CW和CCW在这都是一样的，因为他只是绘制rect的方向，视频中讲错了。但是坐标系没讲错，
        dashShapePath.addRect(0, -DisplayUtils.dip2px(5), dashWidth, DisplayUtils.dip2px(20), Path.Direction.CW);
        //获取刻度间隔
        //获取正常表盘狐线的长度，就把正常弧线的再写一遍，只是为了获取长度。
        Path arcPath = new Path();
//        正常弧线的再写一遍
        arcPath.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE);
        //获取弧线长度
        PathMeasure pathMeasure = new PathMeasure(arcPath, false);
        //获取每个刻度之间的间隔
        float advance = (pathMeasure.getLength() - dashWidth) / 20;
        //设置PathDashPathEffect 块用于draw中画刻度
        dashPathEffect = new PathDashPathEffect(dashShapePath, advance, 0, PathDashPathEffect.Style.MORPH);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //划线
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, paint);

        //画刻度
        paint.setPathEffect(dashPathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        //画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFormMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFormMark(5))) * LENGTH + getWidth() / 2,
                paint);


    }

    /**
     * @param mark 刻度
     * @return
     */
    int getAngleFormMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 + mark);
    }
}
