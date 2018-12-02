package com.example.su.tinkoff

import android.media.Image
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class ShortOrdersAdapter(val orderList: ArrayList<ShortOrder>, var listener: OnRecyclerViewItemClickedListener)
    : RecyclerView.Adapter<ShortOrdersAdapter.CustomViewHolder>() {

    lateinit var mClickListener: OnRecyclerViewItemClickedListener


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
        val tvContract = itemView.findViewById<TextView>(R.id.tvPhoneNumber)
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
        val cardView = itemView.findViewById<CardView>(R.id.orderCardView)
        val imageButton = itemView.findViewById<ImageButton>(R.id.show_on_map_image_button)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var order: ShortOrder = orderList[position]
        holder.tvAddress.text = order.address
        holder.tvContract.text = order.phoneNumber
        holder.tvTime.text = order.time

        holder.cardView.setOnClickListener{
            listener.onClickCard(position)
        }

        holder.imageButton.setOnClickListener{
            listener.onClickImageButton(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.short_order, parent, false)
        return CustomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    interface OnRecyclerViewItemClickedListener{
        fun onClickCard(param: Int)
        fun onClickImageButton(param: Int)
    }
}