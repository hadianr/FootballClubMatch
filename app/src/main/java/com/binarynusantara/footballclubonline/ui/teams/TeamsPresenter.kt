package com.binarynusantara.footballclubonline.ui.teams

import com.binarynusantara.footballclubonline.data.model.TeamsResponse
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.network.TheSportDBApi
import com.binarynusantara.footballclubonline.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class TeamsPresenter(private val view: TeamsView,
                         private val apiRequest: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamsByLeague(leagueName: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getTeamsByLeague(leagueName)),
                        TeamsResponse::class.java
                )
            }
            view.showEventList(data.await().teams)
            view.hideLoading()
        }
    }
}
