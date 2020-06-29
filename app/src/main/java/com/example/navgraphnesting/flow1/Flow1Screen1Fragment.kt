package com.example.navgraphnesting.flow1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.fragment_flow1_screen1.*

/**
 * A simple [Fragment] subclass.
 */
open class Flow1Screen1Fragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flow1_screen1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        btn_move_to_screen2.setOnClickListener {
            val directions =
                Flow1Screen1FragmentDirections.actionFlow1Screen1FragmentToFlow1Screen2Fragment2("Ankit")
//            navController.navigate(R.id.action_flow1Screen1Fragment_to_flow1Screen2Fragment2)
            navController.navigate(directions)
        }

        btn_show_movielist.setOnClickListener {

            val directions =
                Flow1Screen1FragmentDirections.actionFlow1Screen1FragmentToMovieListFragment()
            navController.navigate(directions)
        }
    }

}
