package com.example.relayapplication.view.callbacklistener

import com.example.relayapplication.service.model.SelectApplicationInfo

interface SelectAdapterListener {
    fun onItemVIewClickListener(
        item: SelectApplicationInfo
    )

    fun onDeleteButtonClickListener(
        item: SelectApplicationInfo
    )
}