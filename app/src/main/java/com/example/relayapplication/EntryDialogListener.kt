package com.example.relayapplication

interface EntryDialogListener {
    fun addEntry(
        item: ApplicationInfo,
        entryName: String
    )
}