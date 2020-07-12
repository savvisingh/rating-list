package com.example.getyourguide.ui.userrating.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourguide.BR
import com.example.getyourguide.base.BaseViewModel

class RatingRecyclerAdapter: RecyclerView.Adapter<GenericViewHolder>() {

    var itemList = mutableListOf<BaseViewModel>()

    fun setItems(items: List<BaseViewModel>){
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].resId
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.binding.setVariable(BR.vm, itemList[position])
        holder.binding.executePendingBindings()
    }

}