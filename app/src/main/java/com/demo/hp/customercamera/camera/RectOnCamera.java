package com.demo.hp.customercamera.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class RectOnCamera extends View {
    private static final String TAG = "CameraSurfaceView";
    private int mScreenWidth;
    private int mScreenHeight;
    private Paint mPaint;
    private RectF mRectF;
    private Paint mPaintCircle;
    // 圆
    private Point centerPoint;
    private int radio;

    Canvas canvas;

    Handler handler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {

            mPaintCircle.setColor(Color.TRANSPARENT);
            Log.d("GGGG","canvas = " + canvas);
            invalidate();
        }
    };

    public RectOnCamera(Context context) {
        this(context, null);
    }

    public RectOnCamera(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectOnCamera(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getScreenMetrix(context);
        initView(context);
    }

    private void getScreenMetrix(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
    }

    private void initView(Context context) {
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);// 抗锯齿
        mPaintCircle.setDither(true);// 防抖动
        mPaintCircle.setColor(Color.WHITE);
        mPaintCircle.setStrokeWidth(3);
        mPaintCircle.setStyle(Paint.Style.STROKE);// 空心


        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 抗锯齿
        mPaint.setDither(true);// 防抖动
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);// 空心
        int marginLeft = (int) (mScreenWidth*0.15);
        int marginTop = (int) (mScreenHeight * 0.25);
        mRectF = new RectF(marginLeft, marginTop, mScreenWidth - marginLeft, mScreenHeight - marginTop);

        centerPoint = new Point(mScreenWidth/2, mScreenHeight/2);
        radio = (int) (mScreenWidth*0.1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        mPaint.setColor(Color.RED);
        canvas.drawRect(mRectF, mPaint);
        mPaint.setColor(Color.WHITE);
        Log.i(TAG, "onDraw");
//        canvas.drawCircle(centerPoint.x,centerPoint.y, radio,mPaint);// 外圆
//        canvas.drawCircle(centerPoint.x,centerPoint.y, radio - 20,mPaint); // 内圆


        canvas.drawCircle(centerPoint.x,centerPoint.y, radio,mPaintCircle);// 外圆
        canvas.drawCircle(centerPoint.x,centerPoint.y, radio - 20,mPaintCircle); // 内圆

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                centerPoint = new Point(x, y);
                mPaintCircle.setColor(Color.BLUE);
                invalidate();
                handler.sendEmptyMessageDelayed(0,2000);
                if (mIAutoFocus != null){
                    mIAutoFocus.autoFocus();
                }
                return true;
        }
        return true;
    }

    private IAutoFocus mIAutoFocus;

    /** 聚焦的回调接口 */
    public interface  IAutoFocus{
        void autoFocus();
    }

    public void setIAutoFocus(IAutoFocus mIAutoFocus) {
        this.mIAutoFocus = mIAutoFocus;
    }
}
