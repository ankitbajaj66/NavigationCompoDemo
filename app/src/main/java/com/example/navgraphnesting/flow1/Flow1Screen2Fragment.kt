package com.example.navgraphnesting.flow1


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
class Flow1Screen2Fragment : Fragment() {

    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name = Flow1Screen2FragmentArgs.fromBundle(requireArguments()).name

    }

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
    }

    fun openDialog() {
        val myDialog = MyDialog()
        myDialog.show(activity!!.supportFragmentManager, "My Dialog")
    }

}
