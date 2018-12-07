package com.binarynusantara.footballclubonline.ui.match.lastmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.model.Schedule
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.ui.match.MatchAdapter
import com.binarynusantara.footballclubonline.ui.match.MatchPresenter
import com.binarynusantara.footballclubonline.ui.match.MatchView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment: Fragment(), MatchView {


    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var listSchedules: RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var adapter: MatchAdapter
    private lateinit var spinner: Spinner
    private var match: String = "eventspastleague"
    private var leaguesId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                lparams(matchParent, matchParent)

                spinner = spinner()

                swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipeRefresh
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(matchParent, matchParent)


                        listSchedules = recyclerView{
                            id = R.id.rvLastMatch
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(matchParent, matchParent){
                            centerInParent()
                        }

                        progressBar = progressBar {
                            id = R.id.progressBarLastMatch
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


        initAdapter()

        swipeRefresh.onRefresh {
            presenter.getScheduleList(match,leaguesId)
        }

    }


    private fun initAdapter(){

        adapter = MatchAdapter(schedules)
        listSchedules.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        val idLeague = resources.getIntArray(R.array.id_list_league)
        val spinnerItems = resources.getStringArray(R.array.list_league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leaguesId = idLeague[position]

                presenter.getScheduleList(match, leaguesId)
            }
        }

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showEventList(data: List<Schedule>?) {
        swipeRefresh.isRefreshing = false
        schedules.clear()
        if(data != null) schedules.addAll(data)
        else errorMessage("not found")
        if (schedules.size == 0) errorMessage("not found")
        adapter.notifyDataSetChanged()
    }


    override fun errorMessage(message: String?) {
    }

    companion object {
        fun lastMatchInstance(): LastMatchFragment = LastMatchFragment()
    }

}