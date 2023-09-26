package com.example.kotlin_pr1.UI.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_pr1.R
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            MyApp("Лебедев Даниил", "ИКБО-25-21")
        }
    }
}