package com.example.timer.Domain

class UseCaseReset(val timer: Timer)  {
    fun reset() {
        timer.value = 0
        timer.maxValue = 60
        timer.status = false
    }
}