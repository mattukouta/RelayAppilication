package com.example.relayapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.installed_app_list_item.view.*

class InstalledAppListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val appImage = view.findViewById<ImageView>(R.id.appImage)
    val appName = view.appName.findViewById<TextView>(R.id.appName)
}