package com.example.navgraphnesting.flow1

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.dialog_input_alert.*
import kotlinx.android.synthetic.main.dialog_input_alert.view.*
import java.lang.ClassCastException

/**
 *
 *
 *Created by Ankit Bajaj on 27-06-2020.
 */
class MyInputDialog : AppCompatDialogFragment() {

    var myInputDialogListener: MyInputDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(activity)

        val layoutInflater = LayoutInflater.from(activity)
        val view = layoutInflater.inflate(R.layout.dialog_input_alert, null)

        alertDialog.setView(view).setTitle("Enter Name")

        alertDialog.setPositiveButton("Done") { dialogInterface, i ->
            // Take the input back to UI screen
            dismiss()
            myInputDialogListener?.applyText(view.txt_userName.text.toString())
        }

        alertDialog.setNegativeButton("Cancel") { dialogInterface, i -> dismiss() }
        return alertDialog.create()

    }

    interface MyInputDialogListener {
        fun applyText(userName: String)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            myInputDialogListener = targetFragment as MyInputDialogListener
        } catch (e: ClassCastException) {
            Log.d("onAttach.....Exception", e.printStackTrace().toString())
        }


    }
}