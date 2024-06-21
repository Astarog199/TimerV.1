package com.example.timer.Domain

data class Timer(
    var value: Int = 0,
    var maxValue: Int = 60,
    var status: Boolean = false,
    var countdownMode: Boolean = false,
)