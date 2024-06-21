package com.example.timer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.timer.Domain.Timer
import com.example.timer.Presentation.TimerFragment
import com.example.timer.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider
import kotlin.concurrent.thread



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace<TimerFragment>(R.id.fragment_container_view)
            addToBackStack(TimerFragment::class.java.simpleName)
        }
    }
}