package com.binarynusantara.footballclubonline.ui.match

import com.binarynusantara.footballclubonline.data.model.Schedule

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Schedule>?)
    fun errorMessage(message: String?)
}