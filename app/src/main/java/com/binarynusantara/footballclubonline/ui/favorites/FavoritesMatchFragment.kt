package com.binarynusantara.footballclubonline.ui.favorites

import android.content.Context
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
import com.binarynusantara.footballclubonline.data.db.Favorites
import com.binarynusantara.footballclubonline.ui.match.detailmatch.DetailMatchActivity
import com.binarynusantara.footballclubonline.ui.favorites.adapter.FavoritesAdapter
import com.binarynusantara.footballclubonline.utils.db
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoritesMatchFragment: Fragment(), AnkoComponent<Context>{

    private var favorites: MutableList<Favorites> = mutableListOf()
    private lateinit var listFavorite: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: FavoritesAdapter




    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {

            lparams(matchParent, matchParent)

            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipeRefresh
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listFavorite = recyclerView {
                    lparams(matchParent, wrapContent)
                    id = R.id.rvFavorites
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

            progressBar = progressBar {
                id = R.id.progressBarFavMatch
            }.lparams {
                centerHorizontally()
            }

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(this.ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoritesAdapter(favorites){
            ctx.startActivity<DetailMatchActivity>(
                    "item_eventdetail_id" to "${it.eventId}",
                    "item_home_id" to "${it.homeTeamId}",
                    "item_away_id" to "${it.awayTeamId}"
            )
        }

        listFavorite.adapter = adapter
        initData()
        swipeRefresh.onRefresh {
            favorites.clear()
            initData()
        }
    }

    private fun initData(){
        context?.db?.use {
            swipeRefresh.isRefreshing = false
            progressBar.visibility = View.GONE
            val result = select(Favorites.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorites>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun favoriteMatchInstance() = FavoritesMatchFragment()
    }

}