package com.gb.stopwatch

fun Long.format(): String {
    val millisecondsFormatted = (this % 1000).pad(3)
    val seconds = this / 1000
    val secondsFormatted = (seconds % 60).pad(2)
    val minutes = seconds / 60
    val minutesFormatted = (minutes % 60).pad(2)
    val hours = minutes / 60
    return if (hours > 0) {
        val hoursFormatted = (minutes / 60).pad(2)
        "$hoursFormatted:$minutesFormatted:$secondsFormatted"
    } else {
        "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
    }
}

private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')