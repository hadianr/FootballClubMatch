package com.binarynusantara.footballclubonline.ui.match

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.data.model.Schedule
import com.binarynusantara.footballclubonline.ui.custom.FootballMatchUI
import com.binarynusantara.footballclubonline.ui.match.detailmatch.DetailMatchActivity
import com.binarynusantara.footballclubonline.utils.dateFormatToString
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter (private val schedule: List<Schedule>) : RecyclerView.Adapter<MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(FootballMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return schedule.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(schedule[position])
    }

}


class MatchViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val timeSchedule: TextView = view.find(R.id.text_schedule)
    private val homeTeam: TextView = view.find(R.id.text_hometeam)
    private val homeScore: TextView = view.find(R.id.text_homescore)
    private val awayScore: TextView = view.find(R.id.textawayscore)
    private val awayTeam: TextView = view.find(R.id.text_awayteam)

    fun bindItem(schedule: Schedule){

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(schedule.eventDate)
        val evenDate = dateFormatToString(date)

        timeSchedule.text = evenDate
        homeTeam.text = schedule.eventHomeTeam
        homeScore.text = schedule.eventHomeScore
        awayScore.text = schedule.eventAwayScore
        awayTeam.text = schedule.eventAwayTeam

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailMatchActivity>(
                    "item_eventdetail_id" to schedule.idEvent,
                    "item_home_id" to schedule.idHomeTeam,
                    "item_away_id" to schedule.idAwayTeam
            )
        }

    }


}

