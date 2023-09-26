package com.example.kotlin_pr1.UI.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_pr1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
    }
}