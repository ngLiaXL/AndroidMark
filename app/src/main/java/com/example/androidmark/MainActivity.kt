package com.example.androidmark

import android.content.Intent
import android.os.Bundle
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.androidmark.event.EventActivity
import com.example.androidmark.security.SecurityActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = Adapter()
        mainRecyclerView.adapter = adapter

        adapter.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener() { _, _, position ->
                val action = adapter.getItem(position)?.cls
                startActivity(Intent(this@MainActivity, action))

            }
        addFunction(adapter)

    }

    private fun addFunction(adapter: Adapter) {
        adapter.addData(Item("Security", SecurityActivity::class.java))
        adapter.addData(Item("Event", EventActivity::class.java))
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
    }

    class Adapter : BaseQuickAdapter<Item, BaseViewHolder>(R.layout.layout_fun_item) {

        override fun convert(helper: BaseViewHolder, item: Item) {
            helper.setText(R.id.tv_fun_name, item.name)
            helper.addOnClickListener(R.id.tv_fun_name)
        }

    }

    data class Item(val name: String, val cls: Class<out BaseActivity>)

}