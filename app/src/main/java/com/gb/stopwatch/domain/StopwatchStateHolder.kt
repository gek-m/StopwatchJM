package com.gb.stopwatch.domain

import com.gb.stopwatch.StopwatchState
import com.gb.stopwatch.format

class StopwatchStateHolder(
    private val stopwatchState: StateCalculator,
    private val elapsedTime: ElapsedTime
) : StateHolder {

    private var currentState: StopwatchState = StopwatchState.Paused(0)

    override fun start() {
        currentState = stopwatchState.calculateRunningState(currentState)
    }

    override fun pause() {
        currentState = stopwatchState.calculatePausedState(currentState)
    }

    override fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    override fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTime.calculate(currentState)
        }
        return elapsedTime.format()
    }
}