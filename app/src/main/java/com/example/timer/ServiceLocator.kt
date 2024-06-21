package com.example.timer

import androidx.lifecycle.ViewModelProvider
import com.example.timer.Domain.Timer
import com.example.timer.Domain.UseCaseChangingTimerValue
import com.example.timer.Domain.UseCasePause
import com.example.timer.Domain.UseCaseReset

object ServiceLocator {
    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return ViewModelFactory(
            useCaseChangingTimerValue = provideUseCaseChangingTimerValue(),
            useCaseReset = provideUseCaseReset(),
            useCasePause = provideUseCasePause()
            )
    }

    private val timer = Timer()

    private fun provideUseCaseChangingTimerValue(): UseCaseChangingTimerValue {
        return UseCaseChangingTimerValue(timer = timer)
    }

    private fun provideUseCaseReset(): UseCaseReset {
        return UseCaseReset(timer =  timer)
    }

    private fun provideUseCasePause(): UseCasePause {
        return UseCasePause(timer = timer)
    }
}