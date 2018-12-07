package com.binarynusantara.footballclubonline.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.FrameLayout
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.R.color.colorPrimary
import com.binarynusantara.footballclubonline.ui.favorites.FavoritesMatchFragment
import com.binarynusantara.footballclubonline.ui.match.MatchFragment
import com.binarynusantara.footballclubonline.ui.match.nextmatch.NextMatchFragment
import com.binarynusantara.footballclubonline.ui.teams.TeamsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    private lateinit var mFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        MainActivityUI().apply {
            setContentView(this@MainActivity)
        }


        val bottomNavigationView: BottomNavigationView = find(R.id.navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){

                R.id.nav_match -> {
                    val matchFragment = MatchFragment.matchInstance()
                    addFragment(matchFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_favorites -> {

                    val favFragment = FavoritesMatchFragment.favoriteMatchInstance()
                    addFragment(favFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_teams -> {
                    val teamsFragment = TeamsFragment.teamsInstance()
                    addFragment(teamsFragment)

                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }


        if(savedInstanceState == null) {
            addFragment(MatchFragment.matchInstance())
        }
    }

    class MainActivityUI : AnkoComponent<MainActivity>{
        private lateinit var mFrameLayout: FrameLayout

        @SuppressLint("API")
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout{
                lparams(width = matchParent, height = matchParent){
                    padding = dip(0)
                    margin = dip(0)
                }

                mFrameLayout = frameLayout {
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
        mFragment = fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}

