package com.example.timer.Presentation

import androidx.lifecycle.ViewModel
import com.example.timer.Domain.Timer
import com.example.timer.Domain.UseCaseChangingTimerValue
import com.example.timer.Domain.UseCasePause
import com.example.timer.Domain.UseCaseReset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TimerViewModel(
    private val useCaseChangingTimerValue: UseCaseChangingTimerValue,
    private val useCasePause: UseCasePause,
    private val useCaseReset: UseCaseReset
) : ViewModel() {

    fun startTimer(): Flow<Int> {
       return useCaseChangingTimerValue.start()
    }

    fun pauseTimer() {
        useCasePause.pause()
    }

    fun stopTimer() {
        useCaseReset.reset()
    }

    fun changeTimerMaxValue(arg: Int){
        useCaseChangingTimerValue.changeTimerMaxValue(arg)
    }

    fun getTimerStatus(): Boolean{
        return useCaseChangingTimerValue.getTimerStatus()
    }

    fun modificationManagement(arg: Boolean){
        useCaseChangingTimerValue.modificationManagement(arg)
    }
}