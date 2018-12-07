package com.binarynusantara.footballclubonline.ui.teams.detailteams.players

import com.binarynusantara.footballclubonline.data.model.PlayersResponse
import com.binarynusantara.footballclubonline.data.model.TeamsResponse
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.network.TheSportDBApi
import com.binarynusantara.footballclubonline.ui.match.MatchView
import com.binarynusantara.footballclubonline.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayersPresenter(private val view: PlayersView,
                       private val apiRequest: ApiRepository,
                       private val gson: Gson,
                       private val context: CoroutineContextProvider = CoroutineContextProvider()){


    fun getPlayersByTeam(teamId: Int?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getPlayersByTeam(teamId)),
                        PlayersResponse::class.java
                )
            }
            view.showEventList(data.await().players)
            view.hideLoading()
        }
    }
}