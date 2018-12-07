package com.binarynusantara.footballclubonline.ui.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.model.Teams
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), TeamsView {
    private var teams: MutableList<Teams> = mutableListOf()
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var listTeams: RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var presenter: TeamsPresenter

    private var leagueName: String = "English%20Premier%20League"
    private lateinit var mToolbar: Toolbar



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                lparams(matchParent, matchParent)


                appBarLayout {
                    lparams(width = matchParent, height = wrapContent)

                    mToolbar = toolbar {
                        lparams(width = matchParent, height = wrapContent)
                        id = R.id.toolbar_team
                    }
                }

                spinner = spinner()



                swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipeRefresh
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(matchParent, matchParent)

                        listTeams = recyclerView{
                            id = R.id.rvTeams
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(matchParent, matchParent){
                            centerInParent()
                        }

                        progressBar = progressBar {
                            id = R.id.progressBarTeams
                        }.lparams {
                            centerHorizontally()
                        }

                    }
                }
            }
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(mToolbar)

        mToolbar.setTitleTextColor(ContextCompat.getColor(ctx, R.color.colorWhite))
        initAdapter()

        swipeRefresh.onRefresh {
            presenter.getTeamsByLeague(leagueName)
        }
    }

    private fun initAdapter(){
        adapter = TeamsAdapter(teams)
        listTeams.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.list_league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                leagueName = spinner.selectedItem.toString()
                leagueName = leagueName.replace(" ", "%20")
                presenter.getTeamsByLeague(leagueName)
            }
        }


    }


    override fun showLoading() {
        progressBar.visibility =View.VISIBLE

    }

    override fun hideLoading() {
        progressBar.visibility =View.INVISIBLE

    }

    override fun showEventList(data: List<Teams>?) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        if(data != null) teams.addAll(data)
        else errorMessage("not found")
        if (teams.size == 0) errorMessage("not found")
        adapter.notifyDataSetChanged()
    }

    override fun errorMessage(message: String?) {
    }


    companion object {
        fun teamsInstance(): TeamsFragment = TeamsFragment()
    }

}