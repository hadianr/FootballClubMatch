package com.binarynusantara.footballclubonline.ui.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.ui.match.lastmatch.LastMatchFragment
import com.binarynusantara.footballclubonline.ui.match.nextmatch.NextMatchFragment

class MatchFragment: Fragment(){

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

    }

    companion object {
        fun matchInstance(): MatchFragment = MatchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.main_layout, container, false)

        tabLayout = view.findViewById(R.id.tab_layout)
        toolbar = view.findViewById(R.id.toolbar)
        viewPager = view.findViewById(R.id.view_pager)

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        return view


    }

    private fun setupViewPager(viewPager: ViewPager){
        val adapter = fragmentManager?.let { ViewPagerAdapter(it) }

        val lastMatch = LastMatchFragment.lastMatchInstance()
        adapter?.addFragment(lastMatch, getString(R.string.last_match))

        val nextMatch = NextMatchFragment.nextMatchInstance()
        adapter?.addFragment(nextMatch, getString(R.string.next_match))

        viewPager.adapter = adapter
    }
}