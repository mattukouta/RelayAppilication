package com.example.relayapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstalledAppViewModel: ViewModel() {
    var appList = MutableLiveData<MutableList<ApplicationInfo>>()

    fun addList(appList: ArrayList<ApplicationInfo>){
        this.appList.postValue(appList)
    }
}