package com.binarynusantara.footballclubonline.data.model

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
        @SerializedName("teams")
        val teams: List<Teams>
)