package com.example.relayapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class InstalledAppListAdapter(val context: Context, val appList: ArrayList<ApplicationInfo>): RecyclerView.Adapter<InstalledAppListViewHolder>(){

    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstalledAppListViewHolder {
        return InstalledAppListViewHolder(inflater.inflate(R.layout.installed_app_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: InstalledAppListViewHolder, position: Int) {
        holder.appImage.setImageDrawable(appList[position].appIcon)
        holder.appName.text = appList[position].appName

        holder.itemView.setOnClickListener {
            Log.d("check", position.toString())
        }
    }
}