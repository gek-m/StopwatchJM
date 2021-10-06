package com.gb.stopwatch.domain

interface StateHolder {

    fun start()

    fun pause()

    fun stop()

    fun getStringTimeRepresentation(): String
}