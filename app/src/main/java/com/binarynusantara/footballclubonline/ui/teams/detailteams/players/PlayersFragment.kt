package com.binarynusantara.footballclubonline.ui.teams.detailteams.players

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.model.Players
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayersFragment: Fragment(), PlayersView {

    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var progressBar : ProgressBar
    private lateinit var listPlayers: RecyclerView
    private lateinit var presenter: PlayersPresenter
    private lateinit var adapter: PlayersAdapter
    private var teamsId: Int = 0



    private var players: MutableList<Players> = mutableListOf()

    override fun showLoading() {
        progressBar.visibility =View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility =View.INVISIBLE
    }

    override fun showEventList(data: List<Players>?) {

        swipeRefresh.isRefreshing = false
        players.clear()
        if(data != null) players.addAll(data)
        else errorMessage("not found")
        if (players.size == 0) errorMessage("not found")
        adapter.notifyDataSetChanged()
    }

    override fun errorMessage(message: String?) {
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()

        swipeRefresh.onRefresh {
            presenter.getPlayersByTeam(teamsId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                lparams(matchParent, matchParent)




                swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipeRefresh
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(matchParent, matchParent)

                        listPlayers = recyclerView{
                            id = R.id.rvPlayers
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(matchParent, matchParent){
                            centerInParent()
                        }

                        progressBar = progressBar {
                            id = R.id.progressBarPlayers
                        }.lparams {
                            centerHorizontally()
                        }

                    }
                }
            }
        }.view
    }


    private fun initAdapter(){
        adapter = PlayersAdapter(players)
        listPlayers.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayersPresenter(this, request, gson)

        Log.i("TEAMS", teamsId.toString())
        presenter.getPlayersByTeam(teamsId)


    }



    fun setTeamsId(teamsId: Int){
        this.teamsId = teamsId
    }



    companion object {
        fun playersInstance(teamsId: Int): PlayersFragment {
            val fragment = PlayersFragment()
            fragment.setTeamsId(teamsId)
            return fragment
        }
    }

}