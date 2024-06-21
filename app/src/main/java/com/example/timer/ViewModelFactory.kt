package com.example.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.timer.Domain.UseCaseChangingTimerValue
import com.example.timer.Domain.UseCasePause
import com.example.timer.Domain.UseCaseReset
import com.example.timer.Presentation.TimerViewModel

class ViewModelFactory(
    private val useCaseChangingTimerValue: UseCaseChangingTimerValue,
    private val useCasePause: UseCasePause,
    private val useCaseReset: UseCaseReset
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        when{
            modelClass.isAssignableFrom(TimerViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return TimerViewModel(
                    useCaseChangingTimerValue = useCaseChangingTimerValue,
                    useCasePause =useCasePause,
                    useCaseReset = useCaseReset,
                ) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}