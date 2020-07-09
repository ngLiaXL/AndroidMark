package com.example.androidmark.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.WeekView;

import java.util.List;

/**
 * 下标周视图
 * Created by huanghaibin on 2017/11/29.
 */

public class IndexWeekView extends WeekView {
    private Paint mSchemeBasicPaint = new Paint();
    private int mPadding;
    private int mH, mW;

    private int mRadius;

    public IndexWeekView(Context context) {
        super(context);
        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xff333333);
        mSchemeBasicPaint.setFakeBoldText(true);
        mPadding = dipToPx(getContext(), 4);
        mH = dipToPx(getContext(), 2);
        mW = dipToPx(getContext(), 8);
    }


    @Override
    protected void onPreviewHook() {
        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;

    }

    /**
     * 如果这里和 onDrawScheme 是互斥的，则 return false，
     * return true 会先绘制 onDrawSelected，再绘制onDrawSelected
     *
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param hasScheme hasScheme 非标记的日期
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        float w = mItemWidth / 4f;
        float h = mItemHeight / 4f;
        canvas.drawRoundRect(x + w, y + h, x + w * 3, y + h * 3,
                20, 18, mSelectedPaint);
        return true;

    }

    /**
     * 绘制下标标记
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        mSchemeBasicPaint.setColor(calendar.getSchemeColor());
        List<Calendar.Scheme> schemes = calendar.getSchemes();
        if (schemes == null || schemes.size() == 0) {
            return;
        }
        int space = 1;
        int size = schemes.size();
        int radius = 5;
        int offsetX = mItemWidth / 2 - (radius + space) * (size - 1);
        int indexX = 0;

        for (int i = 0; i < size; i++) {
            Calendar.Scheme scheme = schemes.get(i);

            mSchemePaint.setColor(scheme.getShcemeColor());
            canvas.drawCircle(x + offsetX + indexX, y + mItemHeight - radius, radius, mSchemePaint);
            indexX = (i + 1) * (radius + space) * 2;
        }

    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;

        if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, isSelected ? mSelectTextPaint :
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, isSelected ? mSelectTextPaint :
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
