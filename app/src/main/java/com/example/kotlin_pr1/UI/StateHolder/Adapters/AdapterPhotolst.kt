package com.example.kotlin_pr1.UI.StateHolder.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_pr1.R

class AdapterPhotolst : RecyclerView.Adapter<AdapterPhotolst.PhotoViewHolder>() {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    private var data: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_photos_item, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = data[position]
        holder.dateTextView.text = item
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(list: List<String>) {
        data = list
        notifyDataSetChanged()
    }
}