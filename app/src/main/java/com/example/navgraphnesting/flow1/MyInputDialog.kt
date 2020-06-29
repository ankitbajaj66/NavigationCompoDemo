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
        val alertDialogBuilder = AlertDialog.Builder(activity)

        val layoutInflater = LayoutInflater.from(activity)
        val view = layoutInflater.inflate(R.layout.dialog_input_alert, null)

        alertDialogBuilder.setView(view).setTitle("Enter Name")

        isCancelable = false

        view.btn_done.setOnClickListener {
            if (view.txt_userName.text.toString() != null && view.txt_userName.text.toString() != ""
            ) {
                dismiss()
                myInputDialogListener?.applyText(view.txt_userName.text.toString())
            }
        }

        view.btn_cancel.setOnClickListener {
            dismiss()
            myInputDialogListener?.applyText("Cancelled")
        }
        /*alertDialogBuilder.setPositiveButton("Done", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (view.txt_userName.text.toString() != null && view.txt_userName.text.toString() != ""
                ) {
                    dismiss()
                    myInputDialogListener?.applyText(view.txt_userName.text.toString())
                }
            }
        })

        alertDialogBuilder.setNegativeButton("Cancel") { dialogInterface, i ->
            //            dismiss()
            myInputDialogListener?.applyText("Cancelled")
        }*/

        val alertDialog = alertDialogBuilder.create()

        /* alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
             if (view.txt_userName.text.toString() != null && view.txt_userName.text.toString() != ""
             ) {
                 dismiss()
                 myInputDialogListener?.applyText(view.txt_userName.text.toString())
             }
         }*/
        return alertDialog

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