package com.binarynusantara.footballclubonline.ui.detailmatch

import com.binarynusantara.footballclubonline.data.model.Schedule
import com.binarynusantara.footballclubonline.data.model.Teams

interface DetailView{

    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Schedule>, home: List<Teams>, away: List<Teams>)
}