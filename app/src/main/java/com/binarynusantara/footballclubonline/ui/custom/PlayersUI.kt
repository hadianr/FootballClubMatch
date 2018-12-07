package com.binarynusantara.footballclubonline.ui.custom

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.binarynusantara.footballclubonline.R
import org.jetbrains.anko.*

class PlayersUI: AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout{
            lparams(matchParent, wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView{
                id = R.id.imgPlayersAva
            }.lparams{
                height = dip(54)
                width = dip(54)
            }

            textView {
                id = R.id.tvPlayersName
            }.lparams(dip(0), matchParent){
                weight = 1f
                marginStart = dip(16)
                marginEnd = dip(8)
                gravity = Gravity.CENTER_VERTICAL
            }

            textView {
                id = R.id.tvPlayersPosition
            }.lparams(wrapContent, matchParent){
                gravity = Gravity.CENTER_VERTICAL
            }



        }
    }
}