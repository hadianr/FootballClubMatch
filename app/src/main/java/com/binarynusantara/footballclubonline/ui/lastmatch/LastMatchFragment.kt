package com.binarynusantara.footballclubonline.ui.lastmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.model.Schedule
import com.binarynusantara.footballclubonline.ui.lastmatch.adapter.LastMatchAdapter
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment: Fragment(), LastMatchView {


    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var presenter: LastMatchPresenter
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var listSchedules: RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var adapter: LastMatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            frameLayout {
                lparams(matchParent, matchParent)
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
            presenter.getScheduleList("eventspastleague")
        }

    }


    private fun initAdapter(){
        adapter = LastMatchAdapter(schedules)
        listSchedules.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = LastMatchPresenter(this, request, gson)
        presenter.getScheduleList("eventspastleague")
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