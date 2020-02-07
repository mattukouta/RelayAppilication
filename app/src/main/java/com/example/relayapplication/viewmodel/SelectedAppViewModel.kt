package com.example.relayapplication.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.relayapplication.service.model.SelectApplicationInfo
import com.example.relayapplication.service.repository.SelectApp

class SelectedAppViewModel: ViewModel() {
    var selectAppList = MutableLiveData<ArrayList<SelectApplicationInfo>>()

    fun addSelectList(selectAppList: ArrayList<SelectApplicationInfo>){
        this.selectAppList.postValue(selectAppList)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun appDelete(item: SelectApplicationInfo) {
        SelectApp.selectAppList.removeIf { it.appName == item.appName}
        addSelectList(SelectApp.selectAppList)
    }
}