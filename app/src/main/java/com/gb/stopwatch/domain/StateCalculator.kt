package com.gb.stopwatch.domain

import com.gb.stopwatch.StopwatchState

interface StateCalculator {

    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running

    fun calculatePausedState(oldState: StopwatchState): StopwatchState.Paused
}