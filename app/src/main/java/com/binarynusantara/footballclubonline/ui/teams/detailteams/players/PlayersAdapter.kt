package com.binarynusantara.footballclubonline.ui.teams.detailteams.players

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.model.Players
import com.binarynusantara.footballclubonline.ui.custom.PlayersUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class PlayersAdapter(private val players: List<Players>) : RecyclerView.Adapter<PlayersViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        return PlayersViewHolder(PlayersUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bindItem(players[position])
    }
}


class PlayersViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playersAvatar: ImageView = view.find(R.id.imgPlayersAva)
    private val playersName: TextView = view.find(R.id.tvPlayersName)
    private val playersPosition: TextView = view.find(R.id.tvPlayersPosition)

    fun bindItem(players: Players){
        Picasso.get().load(players.playersCutout).into(playersAvatar)
        playersName.text = players.playersName
        playersPosition.text = players.playersPosition
    }
}