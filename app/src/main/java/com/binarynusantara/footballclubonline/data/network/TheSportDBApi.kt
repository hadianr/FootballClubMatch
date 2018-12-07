package com.binarynusantara.footballclubonline.data.network

import com.binarynusantara.footballclubonline.BuildConfig

object TheSportDBApi {
    private const val strLookUpTeam = "lookupteam"
    private const val strLookUpEvent = "lookupevent"
    private const val strTeamsByLeague = "search_all_teams"
    private const val strPlayersByTeam = "lookup_all_players"


    fun getSchedule(event: String?, idLeague: Int?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + event +".php?id=" + idLeague
    }

    fun getDetailSchedule(idLeague: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + strLookUpEvent +".php?id=" + idLeague
    }

    fun getBadge(idTeam: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + strLookUpTeam +".php?id=" + idTeam

    }

    fun getTeamsByLeague(leagueName: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + strTeamsByLeague +".php?l=" + leagueName
    }

    fun getPlayersByTeam(teamId: Int?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + strPlayersByTeam +".php?id=" + teamId
    }


}