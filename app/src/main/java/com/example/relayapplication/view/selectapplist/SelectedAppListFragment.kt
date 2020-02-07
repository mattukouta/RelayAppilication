package com.example.relayapplication.view.selectapplist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.relayapplication.view.callbacklistener.SelectAdapterListener
import com.example.relayapplication.R
import com.example.relayapplication.service.repository.SelectApp
import com.example.relayapplication.service.model.SelectApplicationInfo
import com.example.relayapplication.databinding.FragmentSelectedAppListBinding
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.relayapplication.view.callbacklistener.DeleteDialogListener
import com.example.relayapplication.view.dialog.DeleteDialog
import com.example.relayapplication.viewmodel.SelectedAppViewModel


class SelectedAppListFragment : Fragment(),
    SelectAdapterListener,
    DeleteDialogListener{

    lateinit var binding: FragmentSelectedAppListBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_selected_app_list, container, false)
        binding.viewModel = ViewModelProvider(this).get(SelectedAppViewModel::class.java)
        binding.lifecycleOwner = this@SelectedAppListFragment
        binding.recyclerView.adapter =
            activity?.applicationContext?.let {
                SelectedAppListAdapter(
                    arrayListOf(),
                    it,
                    this
                )
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.viewModel!!.selectAppList.observe(this@SelectedAppListFragment, Observer {
            val adapter = binding.recyclerView.adapter as SelectedAppListAdapter
            adapter.setData(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title = "チェック済みアプリ一覧"

        binding.viewModel!!.addSelectList(SelectApp.selectAppList)

    }

    override fun onItemVIewClickListener(item: SelectApplicationInfo) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.action = "android.intent.category.LAUNCHER"
        intent.setClassName(item.packageName, item.packageClass)
        startActivity(intent)
    }

    override fun onDeleteButtonClickListener(item: SelectApplicationInfo) {
        val dialog = DeleteDialog(item, this)
        activity?.supportFragmentManager?.let { dialog.show(it, "entry") }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun DeleteApp(item: SelectApplicationInfo) {
        binding.viewModel.appDelete(item)
    }
}
