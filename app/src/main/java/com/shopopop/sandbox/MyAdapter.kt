package com.shopopop.sandbox

import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.listitem.view.*

class MyAdapter(private val arrayDates: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem, parent, false) as View

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(javaClass.simpleName, "position $Int")
        holder.v.textview.text = arrayDates[position]
    }

    override fun getItemCount() = arrayDates.size
}