package com.ljd.helper.choosebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ChooseBtn extends View {
    private int vWidth;
    private int vHeight;
    private Paint paint;
    private Paint paintWhite;
    private Paint paintGray;
    private int radius;
    private Rect rectGreen,rectGray;
    private int x=0;
    private int whiteCirX=0;
    private int middle=0;
    private int space=5;
    private boolean defaultSwitch=true;
    private ChangeListener listener;

    public ChooseBtn(Context context) {
        this(context,null);
    }

    public ChooseBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paintWhite=new Paint();
        paintWhite.setColor(Color.WHITE);
        paintWhite.setAntiAlias(true);
        paintGray=new Paint();
        paintGray.setColor(Color.LTGRAY);
        paintGray.setAntiAlias(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //绿色中间
        canvas.drawRect(rectGreen,paint);
        //绿色的左边
        canvas.drawCircle(radius,radius,radius-space,paint);
        //灰色的右边
        canvas.drawRect(rectGray,paintGray);
        canvas.drawCircle(vWidth-radius,radius,radius-space,paintGray);
        //白色圆形
        canvas.drawCircle(whiteCirX,radius,radius,paintWhite);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        vWidth=getMeasuredWidth();
        vHeight=getMeasuredHeight();
        radius=vHeight/2;
        rectGreen=new Rect(radius,space,vWidth-radius,vHeight-space);
        if (defaultSwitch) {
            whiteCirX=vWidth-radius;
        }else{
            whiteCirX=radius;
        }
        rectGray=new Rect(whiteCirX,space,vWidth-radius,vHeight-space);

        middle=vWidth/2;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x= (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                whiteCirX=whiteCirX-(x-(int) event.getX());
                if (whiteCirX<radius) {
                    whiteCirX=radius;
                }
                if (whiteCirX>vWidth-radius) {
                    whiteCirX=vWidth-radius;
                }
                rectGray.left=whiteCirX;
                x= (int) event.getX();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (whiteCirX>middle) {
                    whiteCirX=vWidth-radius;
                    rectGray.left=whiteCirX;
                    listener.changeListener(true);
                }else{
                    whiteCirX=radius;
                    rectGray.left=whiteCirX;
                    listener.changeListener(false);
                }
                invalidate();
                break;
        }
        return true;
    }

    public void setDefaultSwitch(boolean defaultSwitch){
        this.defaultSwitch=defaultSwitch;
    }

    public interface ChangeListener {
        void changeListener(boolean state);
    }

    public void setChangeListener(ChangeListener listener){
        this.listener=listener;
    }





}
