package com.example.relayapplication


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_installed_app_list.*

class InstalledAppListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_installed_app_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title  = "インストールアプリ一覧"

        val list = getInstalledApp()

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let { InstalledAppListAdapter(it, list) }
    }

    @SuppressLint("LongLogTag")
    fun getInstalledApp(): ArrayList<ApplicationInfo> {
        val packageList = ArrayList<ApplicationInfo>()
        val packageManager = activity?.packageManager

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
                    packageList.add(ApplicationInfo(appName, appIcon, packageName, className))
                } else {
//                    Log.i("check:----------起動不可能なパッケージ名", packageInfo.packageName)
                }
            }
//            Log.d("checkList", packageList[0].toString())
        }

        return packageList
    }

    // アプリ終了時など
    override fun onDestroy() {
        super.onDestroy()
    }
}
