package com.binarynusantara.footballclubonline.ui.teams.detailteams.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI

class OverviewFragment: Fragment() {
    private var overview: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                lparams(matchParent, matchParent){
                    padding = dip(8)
                }
                scrollView {
                    lparams(matchParent, matchParent)
                    textView {
                        text = overview
                    }
                }
            }
        }.view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun setOverview(overview: String){
        this.overview = overview
    }



    companion object {
        fun overviewInstance(overview: String): OverviewFragment{
            val fragment = OverviewFragment()
            fragment.setOverview(overview)
            return fragment
        }
    }

}