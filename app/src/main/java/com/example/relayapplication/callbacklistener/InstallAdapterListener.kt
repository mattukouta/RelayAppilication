package com.example.relayapplication.callbacklistener

import com.example.relayapplication.dataclass.ApplicationInfo

interface InstallAdapterListener {
    fun onItemVIewClickListener(
        item: ApplicationInfo
    )
}