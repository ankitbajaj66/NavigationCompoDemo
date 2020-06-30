package com.example.navigationcompodemo


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_choose_recipient.*

/**
 * A simple [Fragment] subclass.
 */
class ChooseRecipientFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        next_btn.setOnClickListener {
            if (!TextUtils.isEmpty(input_recipient.text.toString())) {

                // Safe Arg Way - compile time this will check passed parameter
                val action =
                    ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                        input_recipient.text.toString()
                    )
                navController.navigate(action)


                /*// Without Safe Arg - it will not check passed value at compile time
                val bundle = bundleOf("recipientData" to input_recipient.text.toString())
                authNavController.navigate(
                    R.id.action_chooseRecipientFragment_to_specifyAmountFragment,
                    bundle
                )*/
            } else {
                Toast.makeText(activity, "Enter a recipient", Toast.LENGTH_LONG).show()
            }
        }

        cancel_btn.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }
}
