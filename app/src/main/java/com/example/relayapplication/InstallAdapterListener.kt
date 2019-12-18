package com.example.relayapplication

interface InstallAdapterListener {
    fun onItemVIewClickListener(
        position: Int,
        appList: MutableList<ApplicationInfo>
    )
}