package com.dahai.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class JPushView extends View {

    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5,bitmap6;
    private LinearGradient linearGradient;
    private Paint paint;

    private int rotate;

    public JPushView(Context context) {
        super(context);

        init();
    }

    public JPushView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public JPushView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        bitmap1 = getBitmap(R.drawable.banner_img_index_1,581/2);
        bitmap2 = getBitmap(R.drawable.banner_img_index_2,785/2);
        bitmap3 = getBitmap(R.drawable.banner_img_index_3,1046/2);
        bitmap4 = getBitmap(R.drawable.banner_img_index_4,1318/2);
        bitmap5 = getBitmap(R.drawable.banner_img_index_5,1759/2);
        bitmap6 = getBitmap(R.drawable.banner_img_index_6,647/2);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private Bitmap getBitmap(@DrawableRes int resId, int width) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),resId,opts);
        int sampleSize = opts.outWidth > width ? opts.outWidth / width + 1 : 1;
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = sampleSize;
        return BitmapFactory.decodeResource(getResources(),resId,opts);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), getMeasuredHeight()
                ,new int[]{Color.rgb(13,136,126), Color.rgb(32,15,85), Color.rgb(13,136,126)},
                null, LinearGradient.TileMode.CLAMP);
        paint.setColor(Color.GREEN);
        paint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint);

        rotate+=1;
        if (rotate>=360) {
            rotate = 0;
        }
        canvas.save();
        canvas.rotate(rotate,getMeasuredWidth()/2,getMeasuredHeight());
        canvas.drawBitmap(bitmap1,getMeasuredWidth()/2-bitmap1.getWidth()/2,getMeasuredHeight()-bitmap1.getHeight()/2,null);
        canvas.restore();
        canvas.save();
        canvas.rotate(-rotate,getMeasuredWidth()/2,getMeasuredHeight());
        canvas.drawBitmap(bitmap2,getMeasuredWidth()/2-bitmap2.getWidth()/2,getMeasuredHeight()-bitmap2.getHeight()/2,null);
        canvas.restore();
        canvas.save();
        canvas.rotate(rotate,getMeasuredWidth()/2,getMeasuredHeight());
        canvas.drawBitmap(bitmap3,getMeasuredWidth()/2-bitmap3.getWidth()/2,getMeasuredHeight()-bitmap3.getHeight()/2,null);
        canvas.restore();
        canvas.save();
        canvas.rotate(-rotate,getMeasuredWidth()/2,getMeasuredHeight());
        canvas.drawBitmap(bitmap4,getMeasuredWidth()/2-bitmap4.getWidth()/2,getMeasuredHeight()-bitmap4.getHeight()/2,null);
        canvas.restore();
        canvas.save();
        canvas.rotate(rotate,getMeasuredWidth()/2,getMeasuredHeight());
        canvas.drawBitmap(bitmap5,getMeasuredWidth()/2-bitmap5.getWidth()/2,getMeasuredHeight()-bitmap5.getHeight()/2,null);
        canvas.restore();
        canvas.save();
        canvas.rotate(-rotate,getMeasuredWidth()/2,getMeasuredHeight());
        canvas.drawBitmap(bitmap6,getMeasuredWidth()/2-bitmap6.getWidth()/2,getMeasuredHeight()-bitmap6.getHeight()/2,null);
        canvas.restore();

        postInvalidateDelayed(36);
    }
}
