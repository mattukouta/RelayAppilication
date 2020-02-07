package com.example.relayapplication.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.relayapplication.R
import com.example.relayapplication.view.callbacklistener.DeleteDialogListener
import com.example.relayapplication.service.model.SelectApplicationInfo

class DeleteDialog(val item: SelectApplicationInfo, val listener: DeleteDialogListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val buidel = AlertDialog.Builder(requireContext())

        val view = activity?.layoutInflater?.inflate(R.layout.dialog_delete, null)
        view?.findViewById<ImageView>(R.id.originalAppIcon)?.setImageDrawable(item.appIcon)
        view?.findViewById<TextView>(R.id.originalAppName)?.text = item.appName

        buidel.setView(view)
            .setPositiveButton("OK") { dialog, id ->
                listener.DeleteApp(item)
            }
            .setNegativeButton("NO") { dialog, id ->

            }

        return buidel.create()
    }
}