package com.example.navigationcompodemo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_confirmation.*

/**
 * A simple [Fragment] subclass.
 */
class ConfirmationFragment : Fragment() {

    lateinit var recipient: String
    lateinit var money: Money

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retriving data using Safe Args
        val args = ConfirmationFragmentArgs.fromBundle(arguments!!)
        recipient = args.recipient
        money = args.money

        /* // Retriving data using normal way
         recipient = arguments!!.getString("recipient")!!
         money = arguments!!.getParcelable("amount")!!*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val amount = money.amount
        val message = "you have sent $amount to $recipient"

        confirmation_message.text = message
    }


}
