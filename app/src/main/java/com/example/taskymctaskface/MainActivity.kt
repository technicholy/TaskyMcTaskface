package com.example.taskymctaskface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val message = "Welcome"
    private val toastLength = Toast.LENGTH_LONG
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toast = Toast.makeText(applicationContext, message, toastLength)
        toast.show()
    }
}