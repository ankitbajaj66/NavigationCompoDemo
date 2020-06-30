package com.example.navgraphnesting.flow1

import androidx.test.espresso.IdlingRegistry
import com.example.util.EspressoIdealingResource
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 *Created by Ankit Bajaj on 30-06-2020.
 */
class EspressoIdealingResourceRule : TestWatcher() {

    private val idlingResource = EspressoIdealingResource.countingIdlingResource

    // After
    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }

    // Before
    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }
}