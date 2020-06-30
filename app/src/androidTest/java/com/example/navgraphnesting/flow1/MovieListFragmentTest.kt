package com.example.navgraphnesting.flow1

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.navigationcompodemo.R
import com.example.util.EspressoIdealingResource
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Ankit Bajaj on 29-06-2020.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdealingResource.countingIdlingResource)
    }

    @After
    fun unRegisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdealingResource.countingIdlingResource)
    }

    @Test
    fun test_launch_MovieListFragment() {

        //  Create a TestNavHostController
        val navController = mockk<NavController>(relaxed = true)

        val scenario = launchFragmentInContainer {
            MovieListFragment().also { fragment ->

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

        // Check the MovieListFragment in UI
        Espresso.onView(ViewMatchers.withId(R.id.recycler_movie_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        val directions =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment2(1)
//
        //  Click on List Item
        Espresso.onView(ViewMatchers.withId(R.id.recycler_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MovieListRecyclerView.MovieViewHolder>(
                1,
                click()
            )
        )

        // Verify the fragment
        verify(exactly = 1) { navController.navigate(directions) }
    }


}