package com.binarynusantara.footballclubonline.data.model

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
        @SerializedName("events")
        val match: List<Schedule>
)