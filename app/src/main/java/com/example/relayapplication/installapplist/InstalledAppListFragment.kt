package com.example.relayapplication.installapplist


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.relayapplication.dataclass.ApplicationInfo
import com.example.relayapplication.callbacklistener.EntryDialogListener
import com.example.relayapplication.callbacklistener.InstallAdapterListener
import com.example.relayapplication.entrydialog.EntryDialog
import com.example.relayapplication.R
import com.example.relayapplication.SelectApp
import com.example.relayapplication.dataclass.SelectApplicationInfo
import com.example.relayapplication.databinding.FragmentInstalledAppListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch



class InstalledAppListFragment : Fragment(),
    InstallAdapterListener,
    EntryDialogListener {

    lateinit var binding: FragmentInstalledAppListBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_installed_app_list, container, false)
        binding.viewModel = ViewModelProvider(this).get(InstalledAppViewModel::class.java)
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = activity?.applicationContext?.let {
            InstalledAppListAdapter(
                arrayListOf(),
                it,
                this
            )
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        binding.viewModel!!.appList.observe(this, Observer {
            val adapter = binding.recyclerView.adapter as InstalledAppListAdapter
            adapter.setData(it)
        })
        this.binding = binding

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getInstalledApp()
    }

    /**
     * RecyclerViewのitemクリック時
     */
    override fun onItemVIewClickListener(
        item: ApplicationInfo
    ) {
        val dialog = EntryDialog(item, this)
        activity?.supportFragmentManager?.let { dialog.show(it, "entry") }
    }

    /**
     * 登録用のダイアログの"OK"クリック時
     */
    override fun addEntry(item: ApplicationInfo, entryName: String) {
        SelectApp.selectAppList.add(
            SelectApplicationInfo(
                item.appName,
                item.appIcon,
                entryName,
                item.packageName,
                item.className
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title  = "インストールアプリ一覧"
    }

    @SuppressLint("LongLogTag")
    fun getInstalledApp(): Job = GlobalScope.launch {
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
            binding.viewModel!!.addList(packageList)
        }
    }

    // アプリ終了時など
    override fun onDestroy() {
        super.onDestroy()
    }
}
