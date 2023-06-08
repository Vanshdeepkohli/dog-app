package com.example.dogappwithrecyclerview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.dogappwithrecyclerview.R
import com.example.dogappwithrecyclerview.network.AllBreedsList
import com.example.dogappwithrecyclerview.network.Message

class DogsBreedAdapter (private val breedsList: List<String>, private val listener : RecyclerViewEvent) : RecyclerView.Adapter<DogsBreedAdapter.MyViewHolder>() {
    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val button : Button = view.findViewById(R.id.BreedName)

        init {
            button.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.button_list_item,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount() = breedsList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.button.text = breedsList[position].toString()
    }

    interface RecyclerViewEvent{
        fun onItemClick(position: Int)
    }
}