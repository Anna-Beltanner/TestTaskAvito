package com.example.testtaskavito

import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_RECYCLER_STATE = "myList"
    }


    private lateinit var myList: MutableList<ItemClass>
    private lateinit var adapter: MyAdapter

    private val myTimer = object : CountDownTimer(5000, 5000) {
        override fun onTick(millisUntilFinished: Long) = Unit

        override fun onFinish() {
            recycler_view.post {
                adapter.insertItem()
            }
            start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTimer.cancel()

        val orientation = this.resources.configuration.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler_view.layoutManager = GridLayoutManager(this, 2)
        } else {
            recycler_view.layoutManager = GridLayoutManager(this, 4)
        }

        val savedList =
                savedInstanceState?.getIntegerArrayList(KEY_RECYCLER_STATE)?.map { ItemClass(it) }
        myList = if (savedList != null) {
            savedList as ArrayList<ItemClass>
        } else {
            generateList(15)
        }
        adapter = MyAdapter(myList)
        recycler_view.adapter = adapter

        myTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        myTimer.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val itemList = myList.map { it.currentNumber } as ArrayList<Int>
        outState.putIntegerArrayList(KEY_RECYCLER_STATE, itemList)
    }

    private fun generateList(size: Int): ArrayList<ItemClass> {
        val list = ArrayList<ItemClass>()
        for (i in 0 until size) {

            val item = ItemClass(i)
            list += item
        }

        return list
    }
}