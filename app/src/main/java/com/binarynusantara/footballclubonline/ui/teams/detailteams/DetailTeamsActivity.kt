package com.binarynusantara.footballclubonline.ui.teams.detailteams

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.ui.match.ViewPagerAdapter
import com.binarynusantara.footballclubonline.ui.teams.detailteams.overview.OverviewFragment
import com.binarynusantara.footballclubonline.ui.teams.detailteams.players.PlayersFragment
import com.squareup.picasso.Picasso

class DetailTeamsActivity: AppCompatActivity(){

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private lateinit var teamsName: TextView
    private lateinit var teamsFormedYears: TextView
    private lateinit var teamsStadium: TextView
    private lateinit var teamsBadge: ImageView

    private lateinit var itemId: String
    private lateinit var itemName: String
    private lateinit var itemBadge: String
    private lateinit var itemStadium: String
    private lateinit var itemFormedYear: String
    private lateinit var itemOverview: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_teams_layout)
        initView()

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        bindView()

    }

    private fun initView(){
        tabLayout = findViewById(R.id.tabDetailTeam)
        toolbar = findViewById(R.id.toolbarDetailTeams)
        viewPager = findViewById(R.id.vpTeams)
        teamsBadge = findViewById(R.id.imgClubBadge)
        teamsName = findViewById(R.id.tvClubName)
        teamsFormedYears = findViewById(R.id.tvClubYearFormed)
        teamsStadium = findViewById(R.id.tvClubStadium)

        if (intent.extras != null) {
            itemId = intent.getStringExtra("itemTeamsId")
            itemName = intent.getStringExtra("itemName")
            itemStadium = intent.getStringExtra("itemStadium")
            itemFormedYear = intent.getStringExtra("itemFormedYear")
            itemBadge = intent.getStringExtra("itemBadge")
            itemOverview = intent.getStringExtra("itemOverview")
        }

    }

    private fun setupViewPager(viewPager: ViewPager){

        val adapter = supportFragmentManager?.let { ViewPagerAdapter(supportFragmentManager) }

        val overview = OverviewFragment.overviewInstance(itemOverview)
        adapter?.addFragment(overview, getString(R.string.overview))

        val players = PlayersFragment.playersInstance(itemId.toInt())
        adapter?.addFragment(players, getString(R.string.players))

        viewPager.adapter = adapter
    }


    private fun bindView() {
        teamsName.text = itemName
        teamsStadium.text = itemStadium
        teamsFormedYears.text = itemFormedYear
        Picasso.get().load(itemBadge).into(teamsBadge)

    }



}