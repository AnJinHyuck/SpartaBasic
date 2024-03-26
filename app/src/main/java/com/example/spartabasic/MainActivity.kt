package com.example.spartabasic

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupButton()
        setRandomValueBetweenOneToHundred()
        setJobAndLaunch()
    }


    private fun setupButton() {
        // TODO("activity_main.xml에 작성되어 있는 button을 findViewById를 사용하여 button이라는 Button 타입의 변수에 할당하기)
        var button = findViewById<Button>(R.id.clickButton)
        // 위 코드가 작성되어야 아래 코드가 수행될 수 있음!
        button.setOnClickListener {
            job?.cancel()
            checkAnswerAndShowToast()
        }
    }

    private fun setRandomValueBetweenOneToHundred() {

        var randomTextView = findViewById<TextView>(R.id.textViewRandom)

        var randomValue = Random.nextInt(1, 101)

        randomTextView.text = randomValue.toString()
    }

    private fun setJobAndLaunch() {

        var textView = findViewById<TextView>(R.id.spartaTextView)
        /*job = lifecycleScope.launch {
            var i = 1
            while (isActive && i <= 100) {
                textView.text = i.toString()
                delay(500)
                i += 1 // ++i, i++
            }
        }*/

        job = lifecycleScope.launch {

            // 아래 /*...*/ 안에 코드를 작성하세요.
            for (i in 1..100) {
                if (isActive) {
                    textView.text = i.toString()
                    delay(500)
                }
            }
        }
    }


    private fun checkAnswerAndShowToast() {
        var textView = findViewById<TextView>(R.id.spartaTextView)
        var randomTextView = findViewById<TextView>(R.id.textViewRandom)
        if (textView.text.toString() == randomTextView.text) {
            Toast.makeText(this, "일치합니다", Toast.LENGTH_SHORT).show()
        } else if (textView != randomTextView) {
            Toast.makeText(this, "일치하지 않습니다", Toast.LENGTH_SHORT).show()
        }
        /**
         * Toast 사용 예)
         * Toast.makeText(this, "메세지", Toast.LENGTH_SHORT).show() // Toast.LENGTH_SHORT 대신 Toast.LENGTH_LONG 또한 사용 가능
         */
    }
}