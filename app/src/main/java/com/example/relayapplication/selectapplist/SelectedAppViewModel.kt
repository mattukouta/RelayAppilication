package com.example.relayapplication.selectapplist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.relayapplication.dataclass.SelectApplicationInfo

class SelectedAppViewModel: ViewModel() {
    var selectAppList = MutableLiveData<ArrayList<SelectApplicationInfo>>()

    fun addSelectList(selectAppList: ArrayList<SelectApplicationInfo>){
        this.selectAppList.postValue(selectAppList)
    }
}