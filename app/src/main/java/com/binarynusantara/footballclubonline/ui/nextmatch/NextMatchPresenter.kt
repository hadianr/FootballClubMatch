package com.binarynusantara.footballclubonline.ui.nextmatch

import com.binarynusantara.footballclubonline.data.model.ScheduleResponse
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.network.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRequest: ApiRepository,
                         private val gson: Gson) {

    fun getScheduleList(match: String?) {
        view.showLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSchedule(match)),
                        ScheduleResponse::class.java
                )
            }
            view.showEventList(data.await().match)
            view.hideLoading()
        }
    }
}
