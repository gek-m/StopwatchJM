package com.gb.stopwatch.di

import com.gb.stopwatch.domain.*
import com.gb.stopwatch.presentation.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {

    fun getViewModel() = module {

        single<TimestampProvider> { TimestampImpl }

        single<ElapsedTime> { ElapsedTimeCalculator(timestampProvider = get()) }

        single<StateCalculator> { StopwatchStateCalculator(elapsedTime = get(), timestampProvider = get()) }

        single<StateHolder> { StopwatchStateHolder(stopwatchState = get(), elapsedTime = get()) }

        viewModel { MainActivityViewModel(stopwatchStateHolder = get()) }
    }
}