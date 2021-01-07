package com.example.testtaskavito

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_item.view.*
import kotlin.random.Random


class MyAdapter(
    private val myList: MutableList<ItemClass>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val deletedItem = mutableListOf<ItemClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myList[position]

        holder.textView1.text = currentItem.currentNumber.toString()
        holder.myRemoveButton.setOnClickListener {

            removeItem(currentItem)

        }


    }

    override fun getItemCount() = myList.size

    fun insertItem() {

        val index: Int = Random.nextInt(myList.size)
        val newItem = if (deletedItem.isEmpty()) {
            ItemClass(myList.size)
        } else {
            deletedItem.removeFirst()
        }


        myList.add(index, newItem)
        notifyItemInserted(index)

    }

    fun removeItem(currentItemClass: ItemClass) {

        val currentIndex = myList.indexOf(currentItemClass)

        deletedItem.add(currentItemClass)

        myList.removeAt(currentIndex)
        notifyItemRemoved(currentIndex)

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.text_view_1
        val myRemoveButton: Button = itemView.my_remove_button
    }
}