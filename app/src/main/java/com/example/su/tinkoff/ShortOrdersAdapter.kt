package com.example.su.tinkoff

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ShortOrdersAdapter(val orderList: ArrayList<ShortOrder>)
    : RecyclerView.Adapter<ShortOrdersAdapter.CustomViewHolder>() {


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
        val tvContract = itemView.findViewById<TextView>(R.id.tvPhoneNumber)
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var order: ShortOrder = orderList[position]
        holder.tvAddress.text = order.address
        holder.tvContract.text = order.phoneNumber
        holder.tvTime.text = order.time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.short_order, parent, false)
        return CustomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}