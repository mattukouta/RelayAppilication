package com.example.relayapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.relayapplication.databinding.InstalledAppListItemBinding

class InstalledAppListAdapter(var appList: MutableList<ApplicationInfo>, val context: Context): RecyclerView.Adapter<myHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        val binding = DataBindingUtil.inflate<InstalledAppListItemBinding>(LayoutInflater.from(parent.context), R.layout.installed_app_list_item, parent, false)

        return myHolder(binding)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        val item = appList[position]
        holder.binding.viewModel = ApplicationInfo(item.appName, item.appIcon, item.packageName, item.className)
        with(holder.binding.root) {
            tag = item
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun setData(items: MutableList<ApplicationInfo>){
        this.appList = items
        notifyDataSetChanged()
    }
}
class myHolder(val binding: InstalledAppListItemBinding): RecyclerView.ViewHolder(binding.root)