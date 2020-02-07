package com.example.relayapplication.viewmodel

import android.content.pm.PackageManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.relayapplication.service.model.ApplicationInfo
import com.example.relayapplication.service.model.SelectApplicationInfo
import com.example.relayapplication.service.repository.SelectApp

class InstalledAppViewModel: ViewModel() {
    var appList = MutableLiveData<MutableList<ApplicationInfo>>()

    fun addList(appList: ArrayList<ApplicationInfo>){
        this.appList.postValue(appList)
    }

    fun addApp(item: ApplicationInfo, entryName: String) {
        val appinfo = SelectApplicationInfo(
            item.appName,
            item.appIcon,
            entryName,
            item.packageName,
            item.className
        )
        var isContain = false

        for (app in SelectApp.selectAppList) {
            if (app.appName == item.appName) {
                isContain = true
            }
        }

        if (!isContain) {
            SelectApp.selectAppList.add(appinfo)
        }
    }

    fun getApps(packageManager: PackageManager?) {
        val packageList = ArrayList<ApplicationInfo>()
        val packageInfoList = packageManager?.getInstalledPackages(
            PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES
        )
        if (packageInfoList != null) {
            for (packageInfo in packageInfoList) {
                if (packageManager.getLaunchIntentForPackage(packageInfo.packageName) != null) {
                    val packageName = packageInfo.packageName
                    val appName = packageInfo.applicationInfo.loadLabel(packageManager).toString()
                    val appIcon = packageInfo.applicationInfo.loadIcon(packageManager)
                    val className = packageManager.getLaunchIntentForPackage(packageInfo.packageName)?.component?.className + ""
//                    Log.i("check:起動可能なパッケージ名", packageName)
//                    Log.i("check:起動可能なクラス名", className)
                    packageList.add(
                        ApplicationInfo(
                            appName,
                            appIcon,
                            packageName,
                            className
                        )
                    )
//                    binding.viewModel!!.addList(ApplicationInfo(appName, appIcon, packageName, className))
                } else {
//                    Log.i("check:----------起動不可能なパッケージ名", packageInfo.packageName)
                }
            }
//            Log.d("checkList", packageList[0].toString())
            addList(packageList)
        }
    }
}