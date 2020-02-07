package com.example.relayapplication.view.selectapplist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.relayapplication.view.callbacklistener.SelectAdapterListener
import com.example.relayapplication.R
import com.example.relayapplication.service.model.SelectApplicationInfo
import com.example.relayapplication.databinding.SelectedAppListItemBinding
import kotlinx.android.synthetic.main.selected_app_list_item.view.*

class SelectedAppListAdapter(var selectAppList: MutableList<SelectApplicationInfo>, val context: Context, val listener: SelectAdapterListener): RecyclerView.Adapter<SelectedAppListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedAppListViewHolder {
        val binding = DataBindingUtil.inflate<SelectedAppListItemBinding>(LayoutInflater.from(parent.context),
            R.layout.selected_app_list_item, parent, false)

        return SelectedAppListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectAppList.size
    }

    override fun onBindViewHolder(holder: SelectedAppListViewHolder, position: Int) {
        val item = selectAppList[position]
        holder.binding.viewModel = SelectApplicationInfo(
            item.appName,
            item.appIcon,
            item.entryAppName,
            item.packageName,
            item.packageClass
        )
        with(holder.binding.root) {
            tag = item
        }

        holder.itemView.setOnClickListener {
            listener.onItemVIewClickListener(item)
        }

        holder.itemView.deleteImage.setOnClickListener {
            listener.onDeleteButtonClickListener(item)
        }

    }

    fun setData(items: MutableList<SelectApplicationInfo>){
        this.selectAppList = items
        notifyDataSetChanged()
    }
}