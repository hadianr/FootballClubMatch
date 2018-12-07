package com.binarynusantara.footballclubonline.ui.teams

import com.binarynusantara.footballclubonline.data.model.Teams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Teams>?)
    fun errorMessage(message: String?)
}