package com.binarynusantara.footballclubonline.ui.match.lastmatch

import com.binarynusantara.footballclubonline.data.model.Schedule

interface LastMatchView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Schedule>?)
    fun errorMessage(message: String?)
}