package com.example.navgraphnesting.flow1

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.navigationcompodemo.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class Flow1Screen1FragmentTest {

    @Test
    fun testViewMove_to_screen2_Onclick() {

        //  Create a TestNavHostController
        val navController = mockk<NavController>(relaxed = true)

        val scenario = launchFragmentInContainer {
            Flow1Screen1Fragment().also { fragment ->

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


        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.btn_move_to_screen2)).perform(click())
        // assertEquals(navController.currentDestination?.id, R.id.flow1Screen2Fragment)

        val action =
            Flow1Screen1FragmentDirections.actionFlow1Screen1FragmentToFlow1Screen2Fragment2("Ankit")

        verify(exactly = 1) { navController.navigate(action) }
    }
}