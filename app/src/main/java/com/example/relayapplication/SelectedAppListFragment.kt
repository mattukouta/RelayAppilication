package com.example.relayapplication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_selected_app_list.*

class SelectedAppListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_app_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {

//            val count = fragmentManager?.backStackEntryCount
//            Log.d("check", count.toString())
//            if (count != null) {
//                if (count > 2) {
//                    findNavController().popBackStack()
//                }
//            }
//            findNavController().navigate(R.id.action_selectedToInstalled)
        }
    }
}
