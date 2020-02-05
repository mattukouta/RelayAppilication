package com.example.relayapplication.callbacklistener

import com.example.relayapplication.dataclass.SelectApplicationInfo

interface DeleteDialogListener {
    fun DeleteApp(
        item: SelectApplicationInfo
    )
}