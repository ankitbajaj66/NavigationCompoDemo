package com.example.navigationcompodemo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Nav to View TransactionFragment
        onView(withId(R.id.view_transactions_btn)).perform(click())

        onView(withId(R.id.view_transaction_container)).check(matches(isDisplayed()))
    }
}