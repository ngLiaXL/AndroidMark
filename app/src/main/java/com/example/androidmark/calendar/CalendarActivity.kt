package com.example.androidmark.calendar

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.androidmark.BaseActivity
import com.example.androidmark.R
import com.haibin.calendarview.CalendarView

/**
 * 周视图显示N周

1、调整WeekViewPager高度 重写onMeasure方法  N * mItemHeight
2、调整BaseWeekView高度 重写onMeasure方法  N * mItemHeight
3、重写WeekView onDraw 方法 并将 onDrawSelected、onDrawScheme、onDrawText 都加上y坐标 参考MonthView
4、调整 CalendarLayout  mContentViewTranslateY 可滑动Y距离   - N * mItemHeight
5、调整 initCalendarForWeekView weekEndDiff 值 加上 (N-1) * 7
 */
class CalendarActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calendar)

        val adapter = Adapter()

        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

        for (i in 0..20) {
            adapter.addData(Item(i.toString()))
        }

        findViewById<CalendarView>(R.id.calendarView).setSchemeDate(SchemeUtil.getSchemes(2020, 7))

    }


    class Adapter : BaseQuickAdapter<Item, BaseViewHolder>(R.layout.layout_fun_item) {


        override fun convert(helper: BaseViewHolder, item: Item) {
            helper.setText(R.id.tv_fun_name, item.name)
        }

    }

    data class Item(val name: String)

}

