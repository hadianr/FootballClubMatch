package com.binarynusantara.footballclubonline.ui.teams

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.model.Teams
import com.binarynusantara.footballclubonline.ui.custom.TeamsUI
import com.binarynusantara.footballclubonline.ui.teams.detailteams.DetailTeamsActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TeamsAdapter(private val teams :List<Teams>) : RecyclerView.Adapter<TeamsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(TeamsUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }
}


class TeamsViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)


    fun bindItem(teams: Teams){
        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName


        itemView.setOnClickListener {
            itemView.context.startActivity<DetailTeamsActivity>(
                    "itemTeamsId" to teams.teamId,
                    "itemBadge" to teams.teamBadge,
                    "itemName" to teams.teamName,
                    "itemStadium" to teams.teamStadium,
                    "itemFormedYear" to teams.teamFormedYear,
                    "itemOverview" to teams.teamDescriptionEN
            )
        }


    }


}
