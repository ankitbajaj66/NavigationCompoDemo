package com.example.navigationcompodemo

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.navgraphnesting.flow1.Flow1Screen1Fragment
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

@RunWith(AndroidJUnit4ClassRunner::class)
class SpecifyAmountFragmentTest {

    @Test
    fun testSpecifyAmountFragment() {

        val recipient = "Ankit"

        val direction =
            ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                recipient
            )

        //  Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        val scenario = launchFragmentInContainer(
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
    }

}