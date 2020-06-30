package com.example.navigationcompodemo

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith


// Tested Fragment in isolation
@RunWith(AndroidJUnit4ClassRunner::class)
class ViewTransactionFragmentTest {

    @Test
    fun testViewTransactionFragment() {
        val scenario = launchFragmentInContainer<ViewTransactionFragment>()


        // verify current view
        Espresso.onView(ViewMatchers.withId(R.id.view_transaction_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.title_food))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.title_food))
            .check(ViewAssertions.matches(ViewMatchers.withText("Food")))

    }
}