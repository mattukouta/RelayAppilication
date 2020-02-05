package com.example.relayapplication.installapplist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.relayapplication.dataclass.ApplicationInfo

class InstalledAppViewModel: ViewModel() {
    var appList = MutableLiveData<MutableList<ApplicationInfo>>()

    fun addList(appList: ArrayList<ApplicationInfo>){
        this.appList.postValue(appList)
    }
}