package com.binarynusantara.footballclubonline.ui.custom

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.binarynusantara.footballclubonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FootballMatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        cardView {
            lparams(matchParent, wrapContent){
                topMargin = dip(8)
                leftMargin = dip(16)
                rightMargin = dip(16)
                radius = dip(4).toFloat()
            }

            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(8)

                textView {
                    id = R.id.text_schedule
                    textColor = Color.parseColor("#3F51B5")
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    setTypeface(null, Typeface.BOLD)
                    textSize = 14f
                }.lparams(width = matchParent, height = matchParent){
                    bottomMargin = dip(8)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        id = R.id.text_hometeam
                        textSize = 16f
                    }.lparams(width = 0, height = matchParent){
                        weight = 1f
                    }

                    textView {
                        id = R.id.text_homescore
                        textSize = 16f
                        setTypeface(null, Typeface.BOLD)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(width = wrapContent, height = matchParent) {
                        leftMargin = dip(8)
                    }

                    textView {
                        id = R.id.text_versus
                        text = resources.getString(R.string.vs)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        textSize = 14f
                        setTypeface(null, Typeface.BOLD)

                    }.lparams(width = 0, height = matchParent){
                        weight = 0.25f
                    }

                    textView {
                        id = R.id.textawayscore
                        setTypeface(null, Typeface.BOLD)
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(width = wrapContent, height = matchParent) {
                        rightMargin = dip(8)
                    }

                    textView {
                        id = R.id.text_awayteam
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams( width = 0, height = matchParent){
                        weight = 1f
                    }
                }
            }.lparams(matchParent, wrapContent) {
                margin = dip(8)
            }
        }
    }
}

