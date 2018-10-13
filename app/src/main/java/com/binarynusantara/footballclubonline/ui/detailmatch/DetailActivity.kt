package com.binarynusantara.footballclubonline.ui.detailmatch

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.binarynusantara.footballclubonline.R
import com.binarynusantara.footballclubonline.R.color.colorAccent
import com.binarynusantara.footballclubonline.R.color.colorPrimary
import com.binarynusantara.footballclubonline.data.db.Favorites
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.model.Schedule
import com.binarynusantara.footballclubonline.data.model.Teams
import com.binarynusantara.footballclubonline.utils.db
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity: AppCompatActivity(), DetailView {


    private lateinit var scheduleDetail: Schedule
    private lateinit var lyEventDetail: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: DetailPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var badgeHome: Teams
    private lateinit var badgeAway: Teams

    private lateinit var idEventDetail: String
    private lateinit var tvHomeShot: TextView
    private lateinit var tvAwayShot: TextView
    private lateinit var tvHomeTeam: TextView
    private lateinit var tvAwayTeam: TextView
    private lateinit var tvHomeFormation: TextView
    private lateinit var tvAwayFormation: TextView
    private lateinit var tvHomeScore: TextView
    private lateinit var tvAwayScore: TextView
    private lateinit var imgHomeBadge: ImageView
    private lateinit var imgAwayBadge: ImageView
    private lateinit var tvDateEvent: TextView
    private lateinit var tvHomeGoalkeeper: TextView
    private lateinit var tvAwayGoalkeeper: TextView
    private lateinit var tvHomeDefense: TextView
    private lateinit var tvAwayDefense: TextView
    private lateinit var tvHomeMidfield: TextView
    private lateinit var tvAwayMidfield: TextView
    private lateinit var tvHomeForward: TextView
    private lateinit var tvAwayForward: TextView

    lateinit var toolbar: ActionBar

    private var itemHomeId: String? = null
    private var itemAwayId: String? = null
    private var favMenu: Menu? = null
    private var isFavorites: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        toolbar = supportActionBar as ActionBar
        toolbar.title = getString(R.string.app_name) + " : Detail Match"

        if (intent.extras != null) {
            idEventDetail = intent.getStringExtra("item_eventdetail_id")
            itemHomeId = intent.getStringExtra("item_home_id")
            itemAwayId = intent.getStringExtra("item_away_id")
            Log.i("ID", idEventDetail)
        }

        initView()
        getEventDetail()
    }


    @SuppressLint("API")
    private fun initView()  {
        swipeRefresh = swipeRefreshLayout {
            setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
            scrollView {
                lparams(width = matchParent, height = matchParent)
                relativeLayout {
                    lparams(width = matchParent, height = matchParent)
                    lyEventDetail = linearLayout {
                        id = R.id.lyEventDetail
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        backgroundColor = Color.WHITE
                        padding = dip(16)

                        tvDateEvent = textView {
                            gravity = Gravity.CENTER
                            textColor = Color.parseColor("#3F51B5")
                            textSize = 16f
                            setTypeface(null, Typeface.BOLD)
                        }.lparams(width = matchParent, height = wrapContent) {
                            topMargin = dip(16)
                            bottomMargin = dip(16)
                        }

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER

                            imgHomeBadge = imageView().lparams(width = dip(100), height = dip(100)) {
                                gravity = Gravity.CENTER
                                topPadding = dip(16)
                                rightMargin = dip(16)
                            }

                            tvHomeScore = textView {
                                gravity = Gravity.CENTER
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent)

                            textView {
                                text = resources.getString(R.string.vs)
                                textColor = Color.parseColor("#3F51B5")
                                textSize = 24f
                            }.lparams(width = wrapContent, height = wrapContent) {
                                leftMargin = dip(8)
                                rightMargin = dip(8)
                            }

                            tvAwayScore = textView {
                                gravity = Gravity.CENTER
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent)

                            imgAwayBadge = imageView().lparams(width = dip(100), height = dip(100)) {
                                gravity = Gravity.CENTER
                                topPadding = dip(16)
                                leftMargin = dip(16)

                            }

                        }.lparams(height = wrapContent, width = wrapContent)

                        //LINEAR LAYOUT TEAM & FORMATION
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f
                            gravity = Gravity.CENTER

                            linearLayout {
                                orientation = LinearLayout.VERTICAL

                                tvHomeTeam = textView {
                                    textColor = colorPrimary
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }

                                tvHomeFormation = textView {
                                    textSize = 14f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }

                            }.lparams(width = 0, height = wrapContent, weight = 0.45f) {
                                margin = dip(16)
                            }

                            linearLayout {
                                orientation = LinearLayout.VERTICAL
                            }.lparams(width = 0, height = wrapContent, weight = 0.1f) {
                                margin = dip(16)
                            }

                            linearLayout {
                                orientation = LinearLayout.VERTICAL

                                tvAwayTeam = textView {
                                    textColor = colorPrimary
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }

                                tvAwayFormation = textView {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    textSize = 14f
                                }

                            }.lparams(width = 0, height = wrapContent, weight = 0.45f) {
                                margin = dip(16)
                            }
                        }

                        view {
                            backgroundColorResource = android.R.color.darker_gray
                        }.lparams(width = matchParent, height = dip(2)) {
                            bottomMargin = dip(48)
                        }

                        //LINEARLAYOUT TOTAL SHOTS
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeShot = textView {
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 18f
                                    text = resources.getString(R.string.shots)
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimary)

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                tvAwayShot = textView {
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }
                        }

                        view {
                            backgroundColorResource = android.R.color.darker_gray
                        }.lparams(width = matchParent, height = dip(2)) {
                            bottomMargin = dip(16)
                        }

                        textView {
                            textSize = 18f
                            text = resources.getString(R.string.lineups)
                            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        }.lparams(width = wrapContent, height = wrapContent)

                        //goalkeeper
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeGoalkeeper = textView {
                                    textSize = 12f

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 12f
                                    text = resources.getString(R.string.gk)
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                tvAwayGoalkeeper = textView {
                                    textSize = 12f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }
                        }

                        //Defense
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeDefense = textView {
                                    textSize = 12f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 12f
                                    text = resources.getString(R.string.df)
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                tvAwayDefense = textView {
                                    textSize = 12f
                                    gravity = right
                                    textAlignment = gravity

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }
                        }

                        //Midfield
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeMidfield = textView {
                                    textSize = 12f

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 12f
                                    text = resources.getString(R.string.mf)
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                tvAwayMidfield = textView {
                                    textSize = 12f
                                    gravity = right
                                    textAlignment = gravity
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }
                        }

                        //Forward
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeForward = textView {
                                    textSize = 12f

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 12f
                                    text = resources.getString(R.string.fw)
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                tvAwayForward = textView {
                                    textSize = 12f
                                    gravity = right
                                    textAlignment = gravity
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }
                        }
                    }
                    progressBar = progressBar {
                        id = R.id.progressBarDetailEvent
                    }.lparams {
                        centerInParent()
                    }
                }
            }
        }
    }

    private fun bindView() {

        swipeRefresh.isRefreshing = false

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(scheduleDetail.eventDate)
        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        tvDateEvent.text = dateEvent
        tvHomeScore.text = scheduleDetail.eventHomeScore
        tvAwayScore.text = scheduleDetail.eventAwayScore
        tvHomeTeam.text = scheduleDetail.eventHomeTeam
        tvHomeFormation.text = scheduleDetail.eventHomeFormation
        tvAwayFormation.text = scheduleDetail.eventAwayFormation
        tvHomeShot.text = scheduleDetail.eventHomeShots
        tvAwayShot.text = scheduleDetail.eventAwayShots
        tvAwayTeam.text = scheduleDetail.eventAwayTeam
        tvHomeGoalkeeper.text = setPlayerTeam(scheduleDetail.eventHomeLineupGoalKeeper)
        tvAwayGoalkeeper.text = setPlayerTeam(scheduleDetail.eventAwayLineupGoalkeeper)
        tvHomeDefense.text = setPlayerTeam(scheduleDetail.eventHomeLineupDefense)
        tvAwayDefense.text = setPlayerTeam(scheduleDetail.eventAwayLineupDefense)
        tvHomeMidfield.text = setPlayerTeam(scheduleDetail.eventHomeLineupMidfield)
        tvAwayMidfield.text = setPlayerTeam(scheduleDetail.eventAwayLineupMidfield)
        tvHomeForward.text = setPlayerTeam(scheduleDetail.eventHomeLineupForward)
        tvAwayForward.text = setPlayerTeam(scheduleDetail.eventAwayLineupForward)

        Picasso.get().load(badgeHome.teamBadge).into(imgHomeBadge)
        Picasso.get().load(badgeAway.teamBadge).into(imgAwayBadge)

    }


    private fun getEventDetail() {

        favoriteState()
        presenter = DetailPresenter(this, ApiRepository(), Gson())
        presenter.getScheduleDetail(idEventDetail, itemHomeId, itemAwayId)

        swipeRefresh.onRefresh {
            presenter.getScheduleDetail(idEventDetail, itemHomeId, itemAwayId)
        }
    }

    private fun setPlayerTeam(playerName: String?): String? {

        return playerName?.split(";".toRegex())?.dropLastWhile {
            it.isEmpty()
        }?.map { it.trim() }?.toTypedArray()?.joinToString("\n")
    }


    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE

        lyEventDetail.visibility = View.VISIBLE
    }

    override fun showLoading() {

        progressBar.visibility = View.VISIBLE
        lyEventDetail.visibility = View.INVISIBLE
    }

    override fun showEventList(data: List<Schedule>, home: List<Teams>, away: List<Teams>) {
        scheduleDetail = data[0]
        badgeAway = away[0]
        badgeHome = home[0]

        bindView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav, menu)
        favMenu = menu
        checkFavorites()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.add_to_favorite -> {
                if (isFavorites) removeFavorite() else addToFavorite()
                isFavorites = !isFavorites
                checkFavorites()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun checkFavorites(){
        if (isFavorites)
            favMenu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp)
        else
            favMenu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_white_24dp)
    }

    private fun favoriteState(){
        db.use {
            val query = select(Favorites.TABLE_FAVORITE).whereArgs("EVENT_ID = {id}", "id" to idEventDetail)
            val result = query.parseList(classParser<Favorites>())
            if (!result.isEmpty()) isFavorites = true
        }
    }

    private fun addToFavorite() {
        try {
            db.use {
                insert(Favorites.TABLE_FAVORITE, Favorites.EVENT_ID to  scheduleDetail.idEvent,
                        Favorites.EVENT_DATE to scheduleDetail.eventDate,
                        Favorites.HOME_TEAM to scheduleDetail.eventHomeTeam,
                        Favorites.HOME_SCORE to scheduleDetail.eventHomeScore,
                        Favorites.AWAY_TEAM to scheduleDetail.eventAwayTeam,
                        Favorites.AWAY_SCORE to scheduleDetail.eventAwayScore,
                        Favorites.HOME_TEAM_ID to itemHomeId,
                        Favorites.AWAY_TEAM_ID to itemAwayId)
            }
            snackbar(swipeRefresh, "Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }

    }

    private fun removeFavorite() {
        try {
            db.use {
                delete(Favorites.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to idEventDetail)
            }
            snackbar(swipeRefresh, "Removed to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()

        }
    }





}
