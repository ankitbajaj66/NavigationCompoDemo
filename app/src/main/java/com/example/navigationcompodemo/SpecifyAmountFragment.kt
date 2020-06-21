package com.example.navigationcompodemo


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal

/**
 * A simple [Fragment] subclass.
 */
class SpecifyAmountFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var recipientData: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retriving data using Safe Args
        recipientData = SpecifyAmountFragmentArgs.fromBundle(arguments!!).recipient

        /* // Retriving data using normal way
        recipientData = arguments!!.getString("recipientData")!!*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = "Sending money to ${recipientData}"
        recipient.text = message
        navController = Navigation.findNavController(view)

        send_btn.setOnClickListener {
            if (!TextUtils.isEmpty(input_amount.text.toString())) {
                val money = Money(BigDecimal(input_amount.text.toString()))
                val action = SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(recipientData, money)
                navController.navigate(action)

                /*val money = Money(BigDecimal(input_amount.text.toString()))
                val bundle = bundleOf("recipient" to recipientData, "amount" to money)
                authNavController.navigate(
                    R.id.action_specifyAmountFragment_to_confirmationFragment,
                    bundle
                )*/
            } else {
                Toast.makeText(activity, "Enter an amount", Toast.LENGTH_LONG).show()
            }
        }

        cancel_btn.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

}
