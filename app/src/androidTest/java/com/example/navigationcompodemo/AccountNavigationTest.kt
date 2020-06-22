package com.example.navigationcompodemo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import kotlinx.android.synthetic.main.fragment_main.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 *Created by Ankit Bajaj on 22-06-2020.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class AccountNavigationTest {

    @Test
    fun test_ViewTransactionFragmentNavigation() {

        // After launching the activity first fragment which is mainfragment will be loaded
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // verify current view
        onView(withId(R.id.main_fragmentcontainer)).check(matches(isDisplayed()))


        // perform click to move to Transaction Fragment
        onView(withId(R.id.view_transactions_btn)).perform(click())
        // ============================== Load Transaction Fragment ==========================

        // check for transaction fragment visibility
        onView(withId(R.id.view_transaction_container)).check(matches(isDisplayed()))

        // pressing back button will take user back to main fragment
        pressBack()

        // Check for main fragment content
        onView(withId(R.id.main_fragmentcontainer)).check(matches(isDisplayed()))
        // ============================================================================================


        // perform click to move to Transaction Fragment
        onView(withId(R.id.send_money_btn)).perform(click())
        // ============================== Load ChooseRecipientFragment ==========================

        // check for chooseRecipientFragment fragment visibility
        onView(withId(R.id.chooseRecipientFragment)).check(matches(isDisplayed()))

        // set data on recipient field
        onView(withId(R.id.input_recipient)).perform(typeText("Ankit Bajaj"))
        //===============================================================================================

        // perform next click to move to Specify amount fragment
        onView(withId(R.id.view_transactions_btn)).perform(click())

        // ============================== Load Specifyamountfragment ==========================
        // Check for main fragment content
        onView(withId(R.id.specifyamountfragment_container)).check(matches(isDisplayed()))

    }


}