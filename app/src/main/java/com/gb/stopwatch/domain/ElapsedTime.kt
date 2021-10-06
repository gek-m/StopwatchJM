package com.gb.stopwatch.domain

import com.gb.stopwatch.StopwatchState

interface ElapsedTime {

    fun calculate(state: StopwatchState.Running): Long
}