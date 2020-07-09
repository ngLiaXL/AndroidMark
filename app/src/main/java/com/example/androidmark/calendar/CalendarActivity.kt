package com.example.androidmark.calendar

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.androidmark.BaseActivity
import com.example.androidmark.R
import com.haibin.calendarview.CalendarView

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

