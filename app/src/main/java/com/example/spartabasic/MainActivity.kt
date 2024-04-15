package com.example.spartabasic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.spartabasic.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    private var job: Job? = null

    private lateinit var binding: ActivityMainBinding

    private val TAG = "Check"
    private var counter = 1
    private var randomValue = (1..100).random()
    private var btn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState != null) {
            randomValue = savedInstanceState.getInt("randomValue")
            counter = savedInstanceState.getInt("counter")
            btn = savedInstanceState.getBoolean("btn")
            binding.spartaTextView.text = counter.toString()
        }

        setupButton()
        setRandomValueBetweenOneToHundred()
        setJobAndLaunch()
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")

        //Log.d("Check","$randomValue")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
        //Log.d("Check","$randomValue")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        if (btn) {
            setJobAndLaunch()
        }
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")


    }

    override fun onPause() {
        super.onPause()
        job?.cancel()
        Log.i(TAG, "onPause")
        //Log.d("Check","$randomValue")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")

    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
        //Log.d("Check","$randomValue")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
        //Log.d("Check","$randomValue")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
        //Log.d("Check","$randomValue")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")
        counter = savedInstanceState.getInt("counter")
        //Log.d("Check","$randomValue")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState.putInt("counter", counter)
        outState.putInt("randomValue", randomValue)
        outState.putBoolean("btn", btn)
        //Log.d("Check","$randomValue")
        Log.d("Checkcounter", "$counter")
        Log.d("Check", "$btn")
        Log.d("Jii", "${binding.spartaTextView.text}")
    }

    private fun setupButton() {
        binding.clickButton.setOnClickListener {
            checkAnswerAndShowToast()
            job?.cancel()
            btn = false
            counter = binding.spartaTextView.text.toString().toInt()
            Log.d("Check", "$btn")
        }
        Log.d("Jii", "${binding.spartaTextView.text}")


    }

    private fun setRandomValueBetweenOneToHundred() {
        binding.textViewRandom.text = randomValue.toString()
    }


    private fun setJobAndLaunch() {
        job?.cancel() // job is uninitialized exception
        job = lifecycleScope.launch {
            while (counter <= 100 && btn) {
                if (isActive) {
                    binding.spartaTextView.text = counter++.toString()
                    delay(1000) // 1초 = 1000
                }
            }
        }
    }

    private fun checkAnswerAndShowToast() {
        val spartaText = binding.spartaTextView.text.toString()
        val randomText = binding.textViewRandom.text.toString()
        if (spartaText == randomText) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

    }
}