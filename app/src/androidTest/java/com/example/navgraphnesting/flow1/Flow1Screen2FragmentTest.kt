package com.example.navgraphnesting.flow1

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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


        onView(withId(R.id.txt_flow1_screen2_data))
            .check(ViewAssertions.matches(isDisplayed()))

    }


}