package com.example.navgraphnesting.flow1

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

/**
 *Created by Ankit Bajaj on 27-06-2020.
 */
class MyDialog : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      //  return super.onCreateDialog(savedInstanceState)
        val alertDialog = AlertDialog.Builder(activity)
        alertDialog.setTitle("Information").setMessage("This is Information Dialog")
            .setPositiveButton("Ok"
            ) { dialogInterface, i -> dismiss() }

        return alertDialog.create()
    }
}