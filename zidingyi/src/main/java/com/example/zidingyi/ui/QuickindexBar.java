package com.example.zidingyi.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 小亚 on 2017/8/14.
 * function:快速索引栏实现思路
 * 1.继承view,覆写构造方法,初始化画笔
 * 2.在OnDrawer方法里绘制字符
 * 3.在OnMeasure方法里测量高度
 * 4.在OntouchEvent事件知道用户具体按住了那个字母
 * 5.定义抽象方法,实现监听回调
 */

public class QuickindexBar extends View {

    private Paint paint;
    //A.要绘制的内容
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };
    private float cellHeight;
    private int cellwidth;
    private int mHeight;
    private float y;
    private float x;
    private float Y;
    private int currentindex;


    public QuickindexBar(Context context) {
        this(context, null);
    }

    public QuickindexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickindexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiPaint();
    }
    private void intiPaint() {
        //创建一个抗锯齿的画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画笔文本加粗
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //颜色
        paint.setColor(Color.WHITE);
        //字体粗细
        paint.setTextSize(15);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //遍历了26个字母,进行坐标计算,进行绘制
        for (int i = 0; i < LETTERS.length; i++) {
            //从数组,根据i去除字母
            String letter = LETTERS[i];
            //计算X轴坐标
            x = cellwidth * 0.5f - paint.measureText(letter) * 0.5f;
            //计算Y轴坐标
            y = cellHeight * 0.5f + paint.measureText(letter) * 0.5f + i * cellHeight;
            canvas.drawText(letter, x, y, paint);
        }
    }

    //A.完成侧拉索引的测量,得到单元格的高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        cellwidth = getMeasuredWidth();
        //获取单元格的高度,自由定义控件总高度,除以所有字母所占用的高度
        cellHeight = mHeight * 1.0f / LETTERS.length;
    }

    //记录用户上一次按下的位置,以便于进行判断这一次按住的位置是否还是上一次安卓的位置,如果是不做任何处理
    private int lastIndex = 1;

    //B.从谢触摸事件,返回值为True,方起效果
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:
                //计算用户按到那个字母的范围,主要是Y轴;
                Y = event.getY();
                currentindex = (int) (Y / cellHeight);
                //为了防止一个字母按住,不停的重复调用,将进行判断,判断是否还是按着上一个字母,是的话就不做任何处理,提高程序的性能


                if (currentindex != lastIndex) {
                    //为了防止角标越界
                    if (currentindex >= 0 && currentindex < LETTERS.length) {
                        String str = LETTERS[currentindex];
                        //设置回调监听
                        if (mOnLetterUpdateListener != null) {
                            mOnLetterUpdateListener.onLetterUpdate(str);
                        }
                        lastIndex = currentindex;
                    }
                }
                //Toast.makeText(getContext(),LETTERS[currentindex],Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }

    //定义接口
    public interface OnLetterUpdateListener {
        void onLetterUpdate(String letter);
    }

    //定义接口对象
    private OnLetterUpdateListener mOnLetterUpdateListener;

    //暴露方法,让外界传过来一个实现接口的类对象
    public void setOnLetterUpdateListener(OnLetterUpdateListener onLetterUpdateListener) {
        mOnLetterUpdateListener = onLetterUpdateListener;
    }
}