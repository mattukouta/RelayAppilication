package com.example.relayapplication.view.callbacklistener

import com.example.relayapplication.service.model.ApplicationInfo

interface InstallAdapterListener {
    fun onItemVIewClickListener(
        item: ApplicationInfo
    )
}