package com.example.navigationcompodemo

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.fragment_main.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Ankit Bajaj on 22-06-2020.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainFragmentTest{

    @Test
    fun test_MainFragmentVisible() {

        val navController = mockk<NavController>(relaxed = true)

        val scenario = launchFragmentInContainer {
            MainFragment().also { fragment ->

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

        // Check main fragment is in the view
        Espresso.onView(ViewMatchers.withId(R.id.main_fragmentcontainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        // launch View Transaction Fragment
        Espresso.onView(ViewMatchers.withId(R.id.view_transactions_btn)).perform(ViewActions.click())

        verify(exactly = 1) { navController.navigate(R.id.action_mainFragment_to_viewTransactionFragment) }


        // launch Send Money Fragment
        Espresso.onView(ViewMatchers.withId(R.id.send_money_btn)).perform(ViewActions.click())

        verify(exactly = 1) { navController.navigate(R.id.action_mainFragment_to_chooseRecipientFragment) }

        // launch View balance Fragment
        Espresso.onView(ViewMatchers.withId(R.id.view_balance_btn)).perform(ViewActions.click())



    }
}