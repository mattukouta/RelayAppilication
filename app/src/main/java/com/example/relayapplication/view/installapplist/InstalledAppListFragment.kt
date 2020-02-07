package com.example.relayapplication.view.installapplist


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.relayapplication.service.model.ApplicationInfo
import com.example.relayapplication.view.callbacklistener.EntryDialogListener
import com.example.relayapplication.view.callbacklistener.InstallAdapterListener
import com.example.relayapplication.view.dialog.EntryDialog
import com.example.relayapplication.R
import com.example.relayapplication.databinding.FragmentInstalledAppListBinding
import com.example.relayapplication.viewmodel.InstalledAppViewModel
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
        binding.viewModel.addApp(item, entryName)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title  = "インストールアプリ一覧"
    }

    @SuppressLint("LongLogTag")
    fun getInstalledApp(): Job = GlobalScope.launch {
        binding.viewModel.getApps(activity?.packageManager)
    }
}
