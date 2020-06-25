package com.example.navigationcompodemo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // This is the nav Controller which has ref to Nav Graph
        navController = Navigation.findNavController(view)

        // View Transaction
        view_transactions_btn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_viewTransactionFragment)
        }

        // Send Money
        send_money_btn.setOnClickListener {

            // This can be done if we dont want to do it in nav_graph on that action
//            val navOptions = NavOptions.Builder().setPopUpTo(R.id.mainFragment, true).build()
//            navController.navigate(
//                R.id.action_mainFragment_to_chooseRecipientFragment,
//                null,
//                navOptions
//            )

             navController.navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
        }

        // View balance
        view_balance_btn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_viewBalanceFragment)
        }

        // Nav Drawer
        nav_drawer.setOnClickListener {
            Toast.makeText(activity, "Navigation Drawer coming soon", Toast.LENGTH_LONG).show()
        }
    }


}
