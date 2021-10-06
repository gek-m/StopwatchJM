package com.gb.stopwatch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.R
import com.gb.stopwatch.databinding.ActivityMainBinding
import com.gb.stopwatch.presentation.viewmodel.MainActivityViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by inject()

    private val scope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (savedInstanceState == null) {
            mainActivityViewModel.init()
        }

        scope.launch {
            mainActivityViewModel.ticker.collect {
                viewBinding.textTime.text = it
            }
        }

        with(viewBinding) {
            buttonStart.setOnClickListener {
                mainActivityViewModel.start()
            }

            buttonPause.setOnClickListener {
                mainActivityViewModel.pause()
            }

            buttonStop.setOnClickListener {
                mainActivityViewModel.stop()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}