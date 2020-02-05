package com.example.relayapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class SelectAppRealm: RealmObject() {
    @PrimaryKey
    var appName: String = ""

    @Required
    var appIcon: ByteArray? = null
    var entryName: String = ""
    var appPackage: String = ""
    var appClass: String = ""
}