package com.example.relayapplication.callbacklistener

import com.example.relayapplication.dataclass.ApplicationInfo

interface EntryDialogListener {
    fun addEntry(
        item: ApplicationInfo,
        entryName: String
    )
}