package com.example.navigationcompodemo

import android.service.autofill.Validators.not
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal


@RunWith(AndroidJUnit4ClassRunner::class)
class SpecifyAmountFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testSpecifyAmountFragment_success() {

        val navController = mockk<NavController>(relaxed = true)
        val recipient = "Ankit1"
        val direction =
            ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                recipient
            )

        val fragmentScenario = launchFragmentInContainer(
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

        onView(ViewMatchers.withId(R.id.input_amount)).check(ViewAssertions.matches(withHint("Enter Amount")))

        // set data on recipient field
        Espresso.onView(ViewMatchers.withId(R.id.input_amount))
            .perform(ViewActions.typeText("10"))

        Espresso.onView(ViewMatchers.withId(R.id.send_btn)).perform(ViewActions.click())

        val money = Money(BigDecimal("10"))
        val action =
            SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(
                recipient,
                money
            )

        verify(exactly = 1) { navController.navigate(action) }

    }

    @Test
    fun testSpecifyAmountFragment_error_toast() {

        val navController = mockk<NavController>(relaxed = true)
        val recipient = "Ankit1"
        val direction =
            ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                recipient
            )

        val fragmentScenario = launchFragmentInContainer(
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

        Espresso.onView(ViewMatchers.withId(R.id.send_btn)).perform(ViewActions.click())

        // check toast visibility
        onView(withText("Enter an amount")).inRoot(ToastMatcher())
            .check(ViewAssertions.matches(isDisplayed()))

    }

}