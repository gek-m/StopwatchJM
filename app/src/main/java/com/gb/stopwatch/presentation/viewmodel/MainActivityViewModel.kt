package com.gb.stopwatch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.gb.stopwatch.Constants.Companion.START_STOPWATCH
import com.gb.stopwatch.domain.StateHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel(
    private val stopwatchStateHolder: StateHolder
) : ViewModel() {

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow("")
    val ticker: StateFlow<String> = mutableTicker

    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
    )

    fun start() {
        if (job == null) startJob()
        stopwatchStateHolder.start()
    }

    private fun startJob() {

        scope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    fun init() {
        mutableTicker.value = START_STOPWATCH
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = START_STOPWATCH
    }
}