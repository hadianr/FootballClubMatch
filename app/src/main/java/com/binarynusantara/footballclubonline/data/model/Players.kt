package com.binarynusantara.footballclubonline.data.model

import com.google.gson.annotations.SerializedName

data class Players(
        @SerializedName("strPlayer")
        var playersName: String?,

        @SerializedName("strCutout")
        var playersCutout: String?,

        @SerializedName("strPosition")
        var playersPosition: String?,

        @SerializedName("strFanart1")
        var playersFantart: String?,

        @SerializedName("strHeight")
        var playersHeight: String?,

        @SerializedName("strWeight")
        var playersWeight: String?,

        @SerializedName("strDescriptionEN")
        var playersDescription: String?
)