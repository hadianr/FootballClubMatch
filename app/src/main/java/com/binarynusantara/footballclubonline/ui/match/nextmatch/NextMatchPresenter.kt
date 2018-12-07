package com.binarynusantara.footballclubonline.ui.match.nextmatch

import com.binarynusantara.footballclubonline.data.model.ScheduleResponse
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.network.TheSportDBApi
import com.binarynusantara.footballclubonline.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class   NextMatchPresenter(private val view: NextMatchView,
                         private val apiRequest: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getScheduleList(match: String?, leagueId: Int?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSchedule(match, leagueId)),
                        ScheduleResponse::class.java
                )
            }
            view.showEventList(data.await().match)
            view.hideLoading()
        }
    }
}
