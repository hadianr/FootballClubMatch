package com.binarynusantara.footballclubonline.ui.match.detailmatch

import com.binarynusantara.footballclubonline.data.model.ScheduleResponse
import com.binarynusantara.footballclubonline.data.model.TeamsResponse
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.network.TheSportDBApi
import com.binarynusantara.footballclubonline.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchPresenter(private val view: DetailMatchView,
                           private val apiRequest: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getScheduleDetail(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?){
        view.showLoading()

        async(context.main){
            val scheduleDetail = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getDetailSchedule(idEvent)),
                        ScheduleResponse::class.java)

            }
            val badgeHome = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getBadge(idHomeTeam)),
                        TeamsResponse::class.java)
            }
            val badgeAway = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getBadge(idAwayTeam)),
                        TeamsResponse::class.java)
            }
            view.showEventList(scheduleDetail.await().match, badgeHome.await().teams,
                    badgeAway.await().teams)
            view.hideLoading()
        }
    }
}