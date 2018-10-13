package com.binarynusantara.footballclubonline.ui.favorites.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.db.Favorites
import com.binarynusantara.footballclubonline.ui.custom.FootballMatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoritesAdapter(private  val favorites: List<Favorites>, private val listener: (Favorites) -> Unit) : RecyclerView.Adapter<FavoritesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(FootballMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }

}

class FavoritesViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val timeSchedule: TextView = view.find(R.id.text_schedule)
    private val homeTeam: TextView = view.find(R.id.text_hometeam)
    private val homeScore: TextView = view.find(R.id.text_homescore)
    private val awayScore: TextView = view.find(R.id.textawayscore)
    private val awayTeam: TextView = view.find(R.id.text_awayteam)

    fun bindItem(favorites: Favorites, listener: (Favorites) -> Unit){

        timeSchedule.text = favorites.eventDate
        homeTeam.text = favorites.homeTeam
        homeScore.text = favorites.homeScore
        awayScore.text = favorites.awayScore
        awayTeam.text = favorites.awayTeam

        itemView.onClick {
            listener(favorites)
        }
    }

}