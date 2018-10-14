package com.binarynusantara.footballclubonline.data.network

import com.binarynusantara.footballclubonline.BuildConfig

object TheSportDBApi {
    private const val idLeague = "4328"
    private const val strLookUpTeam = "lookupteam"
    private const val strLookUpEvent = "lookupevent"

    fun getSchedule(event: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + event +".php?id=" + idLeague
    }

    fun getDetailSchedule(idLeague: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + strLookUpEvent +".php?id=" + idLeague
    }

    fun getBadge(idTeam: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + strLookUpTeam +".php?id=" + idTeam

    }
}