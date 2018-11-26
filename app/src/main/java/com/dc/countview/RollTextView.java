package com.dc.countview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by CZH
 * Create date 2018/10/12
 * Description:
 */
public class RollTextView extends View {

    private Paint mPaint;
    private final float textSize = 40f;

    private List<String> contentList = new ArrayList<>();

    private String newTextStr = "22222222";

    private String currentTextStr = "1111111111";
    private int textOffSetY;
    private Point newTextPosition;
    private Point currentTextPosition;

    public RollTextView(Context context) {
        super(context);
        init();
    }

    public RollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textOffSetY = (int) (textSize * 1.1);

        newTextPosition = new Point(0, textOffSetY);
        currentTextPosition = new Point(0, textOffSetY);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(textSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) mPaint.measureText(currentTextStr), (int) (textSize * 1.2));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawText(currentTextStr, currentTextPosition.x, currentTextPosition.y, mPaint);

        canvas.drawText(newTextStr, newTextPosition.x, newTextPosition.y, mPaint);

    }

    /**
     * @param rollYPercent 滚动百分比0-1
     */
    public void setRollYPercent(float rollYPercent) {
        currentTextPosition.y = (int) (textOffSetY - (textSize * 1.3 * rollYPercent));
        newTextPosition.y = (int) (textOffSetY + textSize * 1.2 - textSize * 1.2 * rollYPercent);//从textOffSetY向下 textSize *1.2 的距离开始向上移动
        invalidate();
    }

    private int index = 0;

    public void rollToNext() {
        if (contentList.size() == 1) {
            currentTextStr = contentList.get(0);
            newTextStr = contentList.get(0);
        } else {
            if (index + 1 >= contentList.size()) {
                currentTextStr = contentList.get(index);
                newTextStr = contentList.get(0);
                index = -1;//为了下边++置为0.
            } else {
                currentTextStr = contentList.get(index);
                newTextStr = contentList.get(index + 1);
            }
        }
        ObjectAnimator.ofFloat(this, "rollYPercent", 0, 1).setDuration(1000).start();
        index++;
    }

    /**
     * 开始滚动
     */
    public void startRoll() {
    }


    public void setContentList(List<String> contentList) {
        index = 0;
        this.contentList = contentList;
    }

}

