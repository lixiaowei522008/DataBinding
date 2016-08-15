package com.xlw.android.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.xlw.android.demo.R;

/**
 * Created by wei on 2016/8/10.
 */
public class FloatCircleView extends View {

    public int width = 300;
    public int height = 300;
    private Paint circlePaint;
    private Paint textPaint;
    private String text = "50%";
    private boolean drag = false;
    private Bitmap bitmap;

    // 布局文件有用
    public FloatCircleView(Context context) {
        super(context);
        initPaints();
    }

    private void initPaints() {
        // 绘制圆的画笔
        circlePaint = new Paint();
        circlePaint.setColor(Color.GRAY);
        // 防止边缘的锯齿
        circlePaint.setAntiAlias(true);

        // 绘制文本的画笔
        textPaint = new Paint();
        textPaint.setTextSize(20);
        textPaint.setColor(Color.RED);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        // 通过bitmap来缩放指定宽高
        bitmap =  Bitmap.createScaledBitmap(bitmap, width, height, true);

    }

    // 布局文件有用, 并且有自定义属性
    public FloatCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public FloatCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    // 代码中有用, 通过new 关键字来创建实例
    public FloatCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaints();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 判断拖动状态来重新绘制
        if(drag) {
            // 拖动状态
            canvas.drawBitmap(bitmap, 0, 0 , null);
        }else {

        //cx：圆心的x坐标。
        //cy：圆心的y坐标。
        //radius：圆的半径。
        //paint：绘制时所使用的画笔。
        canvas.drawCircle(width / 2, height /2, width / 2, circlePaint);
        // 测量文本宽度
        float textWidth = textPaint.measureText(text);
        // text 文本信息
        // x 文字的起始位置
        // y 文本基线坐标
        float x = width /2 - textWidth /2;
        // 获取文字规格
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float dy = (fontMetrics.descent + fontMetrics.ascent) / 2;
        float y = height / 2 + dy;
        canvas.drawText(text, x, y, textPaint);
        }



    }

    /**
     * 设置悬浮窗的状态
     * @param b true 表示是拖动状态
     */
    public void setDragState(boolean b) {
        drag = b;
        invalidate();
    }
}
