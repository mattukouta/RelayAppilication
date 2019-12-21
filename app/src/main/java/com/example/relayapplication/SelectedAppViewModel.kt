package com.example.relayapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectedAppViewModel: ViewModel() {
    var selectAppList = MutableLiveData<ArrayList<SelectApplicationInfo>>()

    fun addSelectList(selectAppList: ArrayList<SelectApplicationInfo>){
        this.selectAppList.postValue(selectAppList)
    }
}