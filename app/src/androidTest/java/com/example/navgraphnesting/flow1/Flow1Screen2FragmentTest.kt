package com.example.navgraphnesting.flow1

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.navigationcompodemo.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4ClassRunner::class)
class Flow1Screen2FragmentTest {


    @Test
    fun testFlow1Screen2Fragment() {
        val name = "Ankit Bajaj"

        // calling direction object to pass the data
        val direction =
            Flow1Screen1FragmentDirections.actionFlow1Screen1FragmentToFlow1Screen2Fragment2(name)

        val scenario = launchFragmentInContainer(direction.arguments) {
            Flow1Screen2Fragment().also { fragment ->

                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        val args = Flow1Screen2FragmentArgs.fromBundle(fragment.requireArguments())
                        assertEquals(args.name, name)
                    }
                }
            }
        }

        onView(withId(R.id.flow1_screen2_container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.txt_flow1_screen2_data))
            .check(ViewAssertions.matches(isDisplayed()))

        // Click button to show the toast
        onView(withId(R.id.btn_show_dialog)).perform(ViewActions.click())

        // Verify dialog
        onView(withText("Information")).check(ViewAssertions.matches(isDisplayed()))

        onView(withText("This is Information Dialog")).check(ViewAssertions.matches(isDisplayed()))


        // Click button on dialog
        onView(withId(android.R.id.button1)).perform(click())

        // Check the dialog not displayed
        onView(withText("Information")).check(doesNotExist())
        onView(withId(android.R.id.button1)).check(doesNotExist())

    }


}