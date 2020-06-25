package com.example.navigationcompodemo

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.navgraphnesting.flow1.Flow1Screen1Fragment
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

@RunWith(AndroidJUnit4ClassRunner::class)
class SpecifyAmountFragmentTest {

    @Test
    fun testSpecifyAmountFragment() {

        val navController = mockk<NavController>(relaxed = true)
        val recipient = "Ankit1"
        val direction =
            ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                recipient
            )

        val fragmentScenario  = launchFragmentInContainer(
            direction.arguments,
            themeResId = R.style.Theme_MaterialComponents
        ) {
            SpecifyAmountFragment().also { fragment ->

                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }

        // verify current view
        Espresso.onView(ViewMatchers.withId(R.id.specifyamountfragment_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // set data on recipient field
        Espresso.onView(ViewMatchers.withId(R.id.input_amount))
            .perform(ViewActions.typeText("10"))

        Espresso.onView(ViewMatchers.withId(R.id.send_btn)).perform(ViewActions.click())

        val money = Money(BigDecimal("10"))
        val action = SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(recipient, money)

//        val action = SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(recipientData, money)
        verify(exactly = 1) { navController.navigate(action) }


        Espresso.onView(ViewMatchers.withId(R.id.cancel_btn)).perform(ViewActions.click())

    }

}