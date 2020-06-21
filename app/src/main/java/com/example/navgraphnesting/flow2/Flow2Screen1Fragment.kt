package com.example.navgraphnesting.flow2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.fragment_flow2_screen1.*

/**
 * A simple [Fragment] subclass.
 */
class Flow2Screen1Fragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flow2_screen1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        btn_move_to_screen2.setOnClickListener {
            navController.navigate(R.id.action_flow2Screen1Fragment_to_flow2Screen2Fragment)
        }
    }
}
