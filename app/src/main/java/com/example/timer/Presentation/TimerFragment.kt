package com.example.timer.Presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.timer.ServiceLocator
import com.example.timer.databinding.FragmentTimerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimerFragment : Fragment() {
    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!
    private val DEFAULT_VALUE = 30F
    private var scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    private val viewModel: TimerViewModel by viewModels(
        factoryProducer = {ServiceLocator.provideViewModelFactory()}
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CheckResult", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formatter = DateTimeFormatter.ofPattern("mm:ss")


        val timer = binding.timer
        val buttonStart = binding.start
        val reset = binding.reset
        val switch = binding.switchTimer
        val progressBar = binding.progress
        val bar = binding.bar
        bar.value = DEFAULT_VALUE
        val localTime = LocalTime.ofSecondOfDay(DEFAULT_VALUE.toLong())
        timer.text = formatter.format(localTime)


        bar.addOnChangeListener { slider, value, _ ->
            viewModel.changeTimerMaxValue(value.toInt())
            val localTime = LocalTime.ofSecondOfDay(value.toLong())
            timer.text = formatter.format(localTime)
        }

        buttonStart.setOnClickListener {
            job = scope.launch {

                if (!viewModel.getTimerStatus()) {
                    buttonStart.text = "Pause"

                    viewModel.startTimer()
                        .collect {
                            withContext(Dispatchers.Main) {
                                progressBar.max = bar.value.toInt()*60
                                val localTime = LocalTime.ofSecondOfDay(it.toLong())
                                timer.text = formatter.format(localTime)
                                progressBar.progress = it
                            }
                        }
                } else {
                    viewModel.pauseTimer()
                    buttonStart.text = "Resume"
                }
            }

            reset.setVisibility(View.VISIBLE)
            bar.isEnabled = false
            switch.isEnabled = false
        }

        reset.setOnClickListener {
            viewModel.stopTimer()
            job?.cancel()
            buttonStart.text = "Start"
            buttonStart.setVisibility(View.VISIBLE)
            reset.setVisibility(View.GONE)
            bar.isEnabled = true
            bar.value = 30f
            switch.isEnabled = true
        }

        switch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modificationManagement(isChecked)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}