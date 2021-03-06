package com.binarynusantara.footballclubonline.ui.match.nextmatch

import com.binarynusantara.footballclubonline.data.model.Schedule

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Schedule>?)
    fun errorMessage(message: String?)
}