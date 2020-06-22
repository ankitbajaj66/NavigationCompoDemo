package com.example.navigationcompodemo

import androidx.core.content.contentValuesOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.android.synthetic.main.fragment_main.*
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Ankit Bajaj on 22-06-2020.
 */
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    // Create a Test nav Host Controller


    @Test
    fun test_NavigationToTestViewTransactionFragment() {

        // Create a Test nav Host Controller
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)

        // Create a graphical FragmentScenario for the View Transaction
        val mainFragmentScenario = launchFragmentInContainer<MainFragment>()

        // Set the NavController property on the fragment
        mainFragmentScenario.onFragment { mainFragment ->
            Navigation.setViewNavController(mainFragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        Espresso.onView(withId(R.id.view_transactions_btn)).perform(click())
        //assertThat(navController.currentDestination?.id).isEqual(R.id.viewTransactionFragment)
        assertEquals(navController.currentDestination?.id,R.id.viewTransactionFragment)
//        assertThat(navController.currentDestination?.id).isEqualTo(R.id.viewTransactionFragment)
    }
}