package com.example.navgraphnesting.flow1

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.navigationcompodemo.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    @Test
    fun test_Test_Details_Fragment_in_view() {
        val movieId = 1

        // calling direction object to pass the data
        val direction =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment2(movieId)

        val scenario = launchFragmentInContainer(direction.arguments) {
            MovieDetailFragment().also { fragment ->

                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        val args = MovieDetailFragmentArgs.fromBundle(fragment.requireArguments())
                        assertEquals(args.movieId, movieId)
                    }
                }
            }
        }

        Espresso.onView(ViewMatchers.withId(R.id.movie_detail_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}