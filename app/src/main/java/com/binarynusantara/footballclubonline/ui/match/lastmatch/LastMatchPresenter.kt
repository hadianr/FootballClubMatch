package com.binarynusantara.footballclubonline.ui.match.lastmatch

import com.binarynusantara.footballclubonline.data.model.ScheduleResponse
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.network.TheSportDBApi
import com.binarynusantara.footballclubonline.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LastMatchPresenter(private val view: LastMatchView,
                         private val apiRequest: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getScheduleList(match: String?, idLeague: Int?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSchedule(match , idLeague)),
                        ScheduleResponse::class.java
                )
            }
            view.showEventList(data.await().match)
            view.hideLoading()
        }
    }

}