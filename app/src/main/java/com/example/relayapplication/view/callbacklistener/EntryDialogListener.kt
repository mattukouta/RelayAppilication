package com.example.relayapplication.view.callbacklistener

import com.example.relayapplication.service.model.ApplicationInfo

interface EntryDialogListener {
    fun addEntry(
        item: ApplicationInfo,
        entryName: String
    )
}