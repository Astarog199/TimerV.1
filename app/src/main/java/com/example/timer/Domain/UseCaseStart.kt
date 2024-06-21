package com.example.timer.Domain


import kotlinx.coroutines.flow.flow

class UseCaseChangingTimerValue (val timer: Timer) {
    fun start() = flow {
        timer.status = true

        while (timer.status) {
            if (!timer.countdownMode) {
                increaseValue()
            } else {
                decreasingValue()
            }
            emit(timer.value)
            Thread.sleep(1000)
        }
    }

    private fun increaseValue() {
        if (timer.value < timer.maxValue && timer.value >= 0) {
            timer.value++
        } else {
            timer.status = false
        }
    }

    private fun decreasingValue() {
        if (timer.maxValue >= 0) {
            timer.value = timer.maxValue--
        } else {
            timer.status = false
        }
    }

    fun changeTimerMaxValue(arg: Int){
        timer.maxValue = arg * 60
    }

    fun getTimerStatus(): Boolean{
        return timer.status
    }

    fun modificationManagement(arg: Boolean){
        timer.countdownMode = arg
    }
}