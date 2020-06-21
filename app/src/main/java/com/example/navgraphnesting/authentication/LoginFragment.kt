package com.example.navgraphnesting.authentication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    lateinit var authNavController: NavController

    lateinit var mainNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainNavController = activity!!.findNavController(R.id.nav_host_fragment_nested)
        authNavController = Navigation.findNavController(view)


        // ============================== This will be navigate in to authentication graph ===========
        // Signup
        btn_signup.setOnClickListener {
            authNavController.navigate(R.id.action_loginFragment_to_signUpFragment2)
        }

        // Forget password
        btn_forgetpassword.setOnClickListener {
            authNavController.navigate(R.id.action_loginFragment_to_forgetPasswordFragment2)
        }
        // ====================================================================================



        // ============================== This will be navigate in to main graph ===========
        // Login Flow1
        btn_login_flow1.setOnClickListener {

            mainNavController.navigate(R.id.action_authenticationFragment_to_flow1Fragment)
        }

        // Login Flow2
        btn_login_flow2.setOnClickListener {
            mainNavController.navigate(R.id.action_authenticationFragment_to_flow2Fragment)
        }
        // ====================================================================================

    }


}
