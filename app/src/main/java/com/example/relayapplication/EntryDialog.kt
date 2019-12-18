package com.example.relayapplication

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment



class EntryDialog(val position: Int, val appList: MutableList<ApplicationInfo>) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val buidel = AlertDialog.Builder(requireContext())

        val view = activity?.layoutInflater?.inflate(R.layout.dialog_entry, null)
        view?.findViewById<ImageView>(R.id.originalAppIcon)?.setImageDrawable(appList[position].appIcon)
        view?.findViewById<TextView>(R.id.originalAppName)?.text = appList[position].appName

        buidel.setView(view)
            .setPositiveButton("OK") { dialog, id ->
                Log.d("checkdialog", view?.findViewById<EditText>(R.id.entry_app_name)?.text.toString())
            }
            .setNegativeButton("No") { dialog, id ->

            }

        return buidel.create()
    }
}