package com.binarynusantara.footballclubonline.ui.match.detailmatch

import com.binarynusantara.footballclubonline.data.model.Schedule
import com.binarynusantara.footballclubonline.data.model.Teams

interface DetailMatchView{

    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Schedule>, home: List<Teams>, away: List<Teams>)
}