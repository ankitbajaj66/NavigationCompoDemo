package com.example.navigationcompodemo

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Ankit Bajaj on 22-06-2020.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainFragmentTest{

    @Test
    fun test_MainFragmentVisible() {

        val scenario = launchFragmentInContainer<MainFragment>()

        // verify current view
        Espresso.onView(ViewMatchers.withId(R.id.main_fragmentcontainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // perform click to move to Transaction Fragment
        Espresso.onView(ViewMatchers.withId(R.id.view_transactions_btn))
            .perform(ViewActions.click())

        // check for transaction fragment visibility
        Espresso.onView(ViewMatchers.withId(R.id.view_transaction_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // pressing back button will take user back to main fragment
        Espresso.pressBack()

        // Check for main fragment content
        Espresso.onView(ViewMatchers.withId(R.id.main_fragmentcontainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}