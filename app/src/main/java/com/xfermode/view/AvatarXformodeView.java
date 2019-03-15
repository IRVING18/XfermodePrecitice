package com.xfermode.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xfermode.DisplayUtils;
import com.xfermode.R;

/**
 * Created by wangzheng on 2019/3/14 10:49 AM.
 * E-mail : ivring11@163.com
 **/
public class AvatarXformodeView extends View {
    public static final float WIDTH   = DisplayUtils.dip2px(300);
    public static final float PADDING = DisplayUtils.dip2px(20);
    public static final float Large = DisplayUtils.dip2px(10);

    RectF circleRectF = new RectF(PADDING, PADDING, WIDTH + PADDING, WIDTH + PADDING);
    RectF circleRectFLarger = new RectF(PADDING-Large, PADDING-Large, WIDTH + PADDING+Large, WIDTH + PADDING+Large);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    Bitmap bitmap;

    public AvatarXformodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_HARDWARE,paint);
    }

    {
        bitmap = getAvator((int) WIDTH, R.drawable.what_the_fuck);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画外部绿色框
        paint.setColor(Color.GREEN);
        canvas.drawOval(circleRectFLarger,paint);
        paint.reset();

        //设置circleRectF范围离屏缓冲
        int save = canvas.saveLayer(circleRectF, paint);
        //画圆形框
        canvas.drawOval(circleRectF, paint);
        paint.setXfermode(xfermode);
        //画图形
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        //恢复离屏缓冲，就是把图形绘制完再绘制到画布上
        paint.setXfermode(null);
        canvas.restoreToCount(save);

    }

    public Bitmap getAvator(int width, @DrawableRes int res) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只测绘宽高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), res, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), res, options);

    }
}
