package com.example.relayapplication.view.installapplist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.relayapplication.service.model.ApplicationInfo
import com.example.relayapplication.view.callbacklistener.InstallAdapterListener
import com.example.relayapplication.R
import com.example.relayapplication.databinding.InstalledAppListItemBinding

class InstalledAppListAdapter(var appList: MutableList<ApplicationInfo>, val context: Context, val listener: InstallAdapterListener): RecyclerView.Adapter<InstalledAppListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstalledAppListViewHolder {
        val binding = DataBindingUtil.inflate<InstalledAppListItemBinding>(LayoutInflater.from(parent.context),
            R.layout.installed_app_list_item, parent, false)

        return InstalledAppListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: InstalledAppListViewHolder, position: Int) {
        val item = appList[position]
        holder.binding.viewModel = ApplicationInfo(
            item.appName,
            item.appIcon,
            item.packageName,
            item.className
        )
        with(holder.binding.root) {
            tag = item
        }

        holder.itemView.setOnClickListener {
            listener.onItemVIewClickListener(appList[position])
        }
    }

    fun setData(items: MutableList<ApplicationInfo>){
        this.appList = items
        notifyDataSetChanged()
    }
}