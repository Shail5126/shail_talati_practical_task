package com.shail.talati.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shail.talati.R
import com.shail.talati.listener.OnItemClickListener
import com.shail.talati.model.Details
import com.squareup.picasso.Picasso

class ListDetailsAdapter(
    val context: Context,
    var list: ArrayList<Details>,
    val listener:OnItemClickListener
) : RecyclerView.Adapter<ListDetailsAdapter.ViewHolder>() {


    //Overrides Methods..............
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val holder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_details, parent, false)
        return ViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position, holder)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val photo: ImageView = itemView.findViewById(R.id.ivPhoto)
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val cardDetails: CardView = itemView.findViewById(R.id.cardDetails)

        fun bindData(position: Int, holder: ViewHolder) {

            holder.title.text = "Title: " + list[position].title
            holder.cardDetails.setOnClickListener {
                listener.onItemClick(position)
            }

            Picasso.get()
                .load(list[position].thumbnailUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.photo)

        }
    }

    fun setData(list: ArrayList<Details>) {
        this.list = list
        notifyDataSetChanged()
    }


}