package com.luthfi.awesomeapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.luthfi.awesomeapp.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun uiTest() {
        onView(withId(R.id.rv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.root)).perform(
            swipeUp()
        )

        Thread.sleep(800)

        onView(withId(R.id.root)).perform(
            swipeDown()
        )

        Thread.sleep(800)

        onView(withId(R.id.action_list)).perform(click())

        Thread.sleep(800)

        onView(withId(R.id.rv_image)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )

        Thread.sleep(800)
        pressBack()

        onView(withId(R.id.action_grid)).perform(click())

        Thread.sleep(800)

        onView(withId(R.id.rv_image)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        Thread.sleep(800)
        pressBack()
    }
}