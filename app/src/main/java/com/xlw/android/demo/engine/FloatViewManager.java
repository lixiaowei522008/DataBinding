package com.xlw.android.demo.engine;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.xlw.android.demo.view.FloatCircleView;

/**
 * Created by wei on 2016/8/10.
 */
public class FloatViewManager {
    private static FloatViewManager mFloatViewManager = null;
    private Context mContext = null;
    private final WindowManager wmManager;
    private FloatCircleView mFloatCircleView;
    private View.OnTouchListener circleviewTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case  MotionEvent.ACTION_DOWN:
                    startX = event.getRawX();
                    startY = event.getRawY();

                    x0 = event.getRawX();

                    break;

                case MotionEvent.ACTION_MOVE:
                    float x = event.getRawX();
                    float y = event.getRawY();
                    // 位移的距离
                    float moveX = x - startX;
                    float moveY = y - startY;
                    wmParams.x += moveX;
                    wmParams.y += moveY;
                    // 设置悬浮窗的状态
                    mFloatCircleView.setDragState(true);
                    // 更新view
                    wmManager.updateViewLayout(mFloatCircleView, wmParams);
                    startX = x;
                    startY = y;
                    break;

                case MotionEvent.ACTION_UP:
                    float x1 = event.getRawX();
                    if(x1 > getDisplayWidth() / 2) {
                        wmParams.x = getDisplayWidth() - mFloatCircleView.width;
                    }else {
                        wmParams.x = 0;
                    }
                    mFloatCircleView.setDragState(false);
                    wmManager.updateViewLayout(mFloatCircleView, wmParams);

                    if(Math.abs(x1 - x0) > 6) {
                        // 拖拽
                        return true;
                    }else {
                        return false;
                    }

                default:
                    break;

            }



            return false;
        }
    };
    private float startX;
    private float startY;
    private WindowManager.LayoutParams wmParams;
    private float x0;

    private FloatViewManager(final Context context) {
        mContext = context;
        wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mFloatCircleView = new FloatCircleView(context);
        mFloatCircleView.setOnTouchListener(circleviewTouchListener);
        mFloatCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "悬浮窗被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static FloatViewManager getInstance(Context context) {
        if(mFloatViewManager == null) {
            synchronized (FloatViewManager.class) {
                if(mFloatViewManager == null) {
                    mFloatViewManager = new FloatViewManager(context);
                }
             }
        }
        return mFloatViewManager;
    }


    public  void showFloatView() {
        wmParams = new WindowManager.LayoutParams();
        wmParams.width = mFloatCircleView.width;
        wmParams.height = mFloatCircleView.height;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        // 不去抢焦点。
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 设置背景为透明颜色。
        wmParams.format = PixelFormat.RGBA_8888;
        wmManager.addView(mFloatCircleView, wmParams);

    }


    private int getDisplayWidth() {
        return wmManager.getDefaultDisplay().getWidth();

    }




}
