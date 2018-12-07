package com.binarynusantara.footballclubonline.ui.nextmatch

import com.binarynusantara.footballclubonline.data.model.Schedule
import com.binarynusantara.footballclubonline.data.model.ScheduleResponse
import com.binarynusantara.footballclubonline.data.network.ApiRepository
import com.binarynusantara.footballclubonline.data.network.TheSportDBApi
import com.binarynusantara.footballclubonline.ui.match.nextmatch.NextMatchPresenter
import com.binarynusantara.footballclubonline.ui.match.nextmatch.NextMatchView
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private
    lateinit var view: NextMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository


    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getScheduleList() {
        val schedules: MutableList<Schedule> = mutableListOf()
        val response = ScheduleResponse(schedules)
        val match = "eventsnextleague"
        val leagueId = 4328

        `when` (gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSchedule(match, leagueId)),
                ScheduleResponse::class.java
        )).thenReturn(response)

        presenter.getScheduleList(match,leagueId)

        verify(view).showLoading()
        verify(view).showEventList(schedules)
        verify(view).hideLoading()
    }
}