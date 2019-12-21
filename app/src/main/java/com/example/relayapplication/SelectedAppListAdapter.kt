package com.example.relayapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.relayapplication.databinding.SelectedAppListItemBinding

class SelectedAppListAdapter(var selectAppList: MutableList<SelectApplicationInfo>, val context: Context, val listener: SelectAdapterListener): RecyclerView.Adapter<SelectedAppListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedAppListViewHolder {
        val binding = DataBindingUtil.inflate<SelectedAppListItemBinding>(LayoutInflater.from(parent.context), R.layout.selected_app_list_item, parent, false)

        return SelectedAppListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectAppList.size
    }

    override fun onBindViewHolder(holder: SelectedAppListViewHolder, position: Int) {
        val item = selectAppList[position]
        holder.binding.viewModel = SelectApplicationInfo(item.appName, item.appIcon, item.entryAppName, item.packageName, item.packageClass)
        with(holder.binding.root) {
            tag = item
        }

        holder.itemView.setOnClickListener {
            listener.onItemVIewClickListener(item)
        }
    }

    fun setData(items: MutableList<SelectApplicationInfo>){
        this.selectAppList = items
        notifyDataSetChanged()
    }
}