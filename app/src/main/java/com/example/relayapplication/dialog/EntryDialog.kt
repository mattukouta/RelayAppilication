package com.example.relayapplication.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.relayapplication.dataclass.ApplicationInfo
import com.example.relayapplication.callbacklistener.EntryDialogListener
import com.example.relayapplication.R


class EntryDialog(val item: ApplicationInfo, val listener: EntryDialogListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val buidel = AlertDialog.Builder(requireContext())

        val view = activity?.layoutInflater?.inflate(R.layout.dialog_entry, null)
        view?.findViewById<ImageView>(R.id.originalAppIcon)?.setImageDrawable(item.appIcon)
        view?.findViewById<TextView>(R.id.originalAppName)?.text = item.appName

        buidel.setView(view)
            .setPositiveButton("OK") { dialog, id ->

                listener.addEntry(item, view?.findViewById<EditText>(R.id.entry_app_name)?.text.toString())
            }
            .setNegativeButton("NO") { dialog, id ->

            }

        return buidel.create()
    }
}