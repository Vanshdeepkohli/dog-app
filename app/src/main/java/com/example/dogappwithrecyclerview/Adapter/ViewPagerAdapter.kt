package com.example.dogappwithrecyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogappwithrecyclerview.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class ViewPagerAdapter(private val context : Context, private val imageList : List<String>, private val listener : AdapterViewInterface) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val iv : ShapeableImageView = view.findViewById(R.id.dogImg)
        val fabShare : FloatingActionButton = view.findViewById(R.id.fabShare)

        init {
            fabShare.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        Glide.with(context).load(imageList[position]).into(holder.iv)
    }


    interface AdapterViewInterface{
        fun onItemClick(position: Int)
    }
}