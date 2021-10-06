package com.gb.stopwatch.domain

object TimestampImpl : TimestampProvider {

    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}