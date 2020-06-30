package com.example.navgraphnesting.flow1


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.fragment_flow1_screen2.*
import kotlinx.android.synthetic.main.fragment_flow1_screen2.view.*

/**
 * A simple [Fragment] subclass.
 */
class Flow1Screen2Fragment : Fragment(), MyInputDialog.MyInputDialogListener {
    val name: String by lazy { Flow1Screen2FragmentArgs.fromBundle(requireArguments()).name }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flow1_screen2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_flow1_screen2_data.text = txt_flow1_screen2_data.text.toString() + name

        btn_show_dialog.setOnClickListener {
            openDialog()
        }

        btn_show_input_dialog.setOnClickListener {
            showInputDialog()
        }
    }

    private fun openDialog() {
        val myDialog = MyDialog()
//        myDialog.isCancelable = false
        myDialog.show(this@Flow1Screen2Fragment.parentFragmentManager, "My_dialog")
    }

    private fun showInputDialog() {
        val myInputDialog = MyInputDialog()

        // when we need to make fragment to fragment communication then we need to set the target fragment
        myInputDialog.setTargetFragment(this@Flow1Screen2Fragment, 1)
        myInputDialog.show(this@Flow1Screen2Fragment.parentFragmentManager, "My_input_dialog")
    }

    override fun applyText(userName: String) {
        txt_flow1_screen2_data.text = "Entered User name is $userName"
    }
}
