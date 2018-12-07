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

class TeamsUI : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView{
                id = R.id.team_badge
            }.lparams{
                height = dip(54)
                width = dip(54)
            }

            textView{
                id = R.id.team_name
                textSize = 16f
            }. lparams{
                margin = dip(15)
            }

        }
    }

}