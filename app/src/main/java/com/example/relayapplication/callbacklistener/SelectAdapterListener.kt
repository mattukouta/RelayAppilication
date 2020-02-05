package com.example.relayapplication.callbacklistener

import com.example.relayapplication.dataclass.SelectApplicationInfo

interface SelectAdapterListener {
    fun onItemVIewClickListener(
        item: SelectApplicationInfo
    )

    fun onDeleteButtonClickListener(
        item: SelectApplicationInfo
    )
}