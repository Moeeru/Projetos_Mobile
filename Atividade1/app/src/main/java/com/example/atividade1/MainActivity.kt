package com.example.atividade1

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class MainActivity : Activity() {
    private val delayMillis = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela0)

        Handler(Looper.getMainLooper()).postDelayed({
            val tv1 = findViewById<TextView>(R.id.textView)
            val tv2 = findViewById<TextView>(R.id.textView5)
            val tv3 = findViewById<TextView>(R.id.textView6)

            tv1?.text = getString(R.string.programming_for_mobile_devices)
            tv2?.text = getString(R.string.linear_layout)
            tv3?.text = getString(R.string.student_name)
        }, delayMillis)
    }
}
