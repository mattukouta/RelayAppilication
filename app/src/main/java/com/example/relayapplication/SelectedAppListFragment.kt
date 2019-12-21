package com.example.relayapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.relayapplication.databinding.FragmentSelectedAppListBinding

class SelectedAppListFragment : Fragment(), SelectAdapterListener {

    lateinit var binding: FragmentSelectedAppListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_selected_app_list, container, false)
        binding.viewModel = ViewModelProviders.of(this).get(SelectedAppViewModel::class.java)
        binding.lifecycleOwner = this
        binding.recyclerView.adapter =
            activity?.applicationContext?.let { SelectedAppListAdapter(arrayListOf(), it, this) }
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.viewModel!!.selectAppList.observe(this, Observer {
            val adapter = binding.recyclerView.adapter as SelectedAppListAdapter
            adapter.setData(it)
        })
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title = "チェック済みアプリ一覧"

        binding.viewModel!!.addSelectList(SelectApp.selectAppList)

    }

    override fun onItemVIewClickListener(item: SelectApplicationInfo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
