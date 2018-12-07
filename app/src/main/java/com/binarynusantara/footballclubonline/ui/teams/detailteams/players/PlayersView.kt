package com.binarynusantara.footballclubonline.ui.teams.detailteams.players

import com.binarynusantara.footballclubonline.data.model.Players

interface PlayersView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Players>?)
    fun errorMessage(message: String?)
}