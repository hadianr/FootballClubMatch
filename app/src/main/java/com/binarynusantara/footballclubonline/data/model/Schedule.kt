package com.binarynusantara.footballclubonline.data.model

import com.google.gson.annotations.SerializedName

data class Schedule (
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    @SerializedName("strEvent")
    var eventName: String? = null,

    @SerializedName("dateEvent")
    var eventDate: String? = null,

    @SerializedName("strHomeTeam")
    var eventHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var eventAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var eventHomeScore: String? = null,

    @SerializedName("intAwayScore")
    var eventAwayScore: String? = null,

    @SerializedName("strHomeFormation")
    var eventHomeFormation: String? = null,

    @SerializedName("strAwayFormation")
    var eventAwayFormation: String? = null,

    @SerializedName("intHomeShots")
    var eventHomeShots: String? = null,

    @SerializedName("intAwayShots")
    var eventAwayShots: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var eventHomeLineupGoalKeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var eventHomeLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var eventHomeLineupMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var eventHomeLineupForward: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var eventAwayLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var eventAwayLineupDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var eventAwayLineupMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var eventAwayLineupForward: String? = null
    )
