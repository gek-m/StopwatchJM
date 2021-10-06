package com.gb.stopwatch.domain

import com.gb.stopwatch.StopwatchState

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
): ElapsedTime {

    override fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}