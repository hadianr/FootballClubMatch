package com.binarynusantara.footballclubonline.data.model

import com.google.gson.annotations.SerializedName

data class Teams(
        @SerializedName("idTeam")
        var teamId: String?,
        @SerializedName("strTeam")
        var teamName: String?,
        @SerializedName("strTeamBadge")
        var teamBadge: String?,
        @SerializedName("strDescriptionEN")
        var teamDescriptionEN: String?,
        @SerializedName("intFormedYear")
        var teamFormedYear: String?,
        @SerializedName("strStadium")
        var teamStadium: String?

)