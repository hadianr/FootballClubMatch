package com.binarynusantara.footballclubonline.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.R.color.colorPrimary
import com.binarynusantara.footballclubonline.ui.lastmatch.LastMatchFragment
import com.binarynusantara.footballclubonline.ui.nextmatch.NextMatchFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = supportActionBar as ActionBar
        toolbar.title = getString(R.string.app_name) + " : Last Match"

        MainActivityUI().setContentView(this)




        val bottomNavigationView: BottomNavigationView = find(R.id.navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){

                R.id.nav_prev_match -> {
                    val lastFragment = LastMatchFragment.lastMatchInstance()
                    addFragment(lastFragment)
                    toolbar.title = getString(R.string.app_name) + " : Last Match"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_next_match -> {
                    val nextFragment = NextMatchFragment.nextMatchInstance()
                    addFragment(nextFragment)
                    toolbar.title = getString(R.string.app_name) + " : Next Match"

                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }


        if(savedInstanceState == null) {
            addFragment(LastMatchFragment.lastMatchInstance())
        }
    }

    class MainActivityUI : AnkoComponent<MainActivity>{
        @SuppressLint("API")
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout{
                lparams(width = matchParent, height = matchParent){
                    padding = dip(0)
                    margin = dip(0)
                }

                frameLayout {
                    id = R.id.container
                }.lparams(width = matchParent, height = matchParent, weight = 1F)

                bottomNavigationView {
                    id = R.id.navigation
                    inflateMenu(R.menu.menu)
                    itemBackgroundResource = colorPrimary
                    backgroundColor = android.R.attr.windowBackground
                    itemTextColor = ContextCompat.getColorStateList(context, R.color.colorWhite)
                    itemIconTintList = ContextCompat.getColorStateList(context, R.color.colorWhite)
                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.BOTTOM
                    padding = dip(0)
                    margin = dip(0)
                }

            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}

