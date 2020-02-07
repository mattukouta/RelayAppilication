package com.example.relayapplication.view.callbacklistener

import com.example.relayapplication.service.model.SelectApplicationInfo

interface DeleteDialogListener {
    fun DeleteApp(
        item: SelectApplicationInfo
    )
}