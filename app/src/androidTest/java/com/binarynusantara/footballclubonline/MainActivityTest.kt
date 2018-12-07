package com.binarynusantara.footballclubonline

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.binarynusantara.footballclubonline.R.id.*
import com.binarynusantara.footballclubonline.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)
    private val wait: Long = 3000


    @Test
    fun testAppBehaviour(){
        Espresso.onView(ViewMatchers.withId(container)).check(ViewAssertions.matches(isDisplayed()))
//        Espresso.onView(ViewMatchers.withId(nav_last_match)).check(ViewAssertions.matches(isDisplayed()))
//        Espresso.onView(ViewMatchers.withId(nav_next_match)).check(ViewAssertions.matches(isDisplayed()))

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(rvLastMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(rvLastMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(llDetailMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(llDetailMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        Espresso.onView(ViewMatchers.withId(nav_next_match)).perform(ViewActions.click())

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(rvNextMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(rvNextMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(llDetailMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Espresso.pressBack()


        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(nav_favorites)).perform(ViewActions.click())

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(rvFavorites)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(rvFavorites)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        Thread.sleep(wait)
        Espresso.onView(ViewMatchers.withId(llDetailMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Espresso.pressBack()
        Thread.sleep(wait)

        Espresso.onView(ViewMatchers.withId(swipeRefresh)).perform(ViewActions.swipeDown())



    }



}