package com.example.kotlin_pr1.UI.View

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_pr1.R
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val students = listOf(
            Student("", ""),
            Student("Лебедев Даниил", "ИКБО-25-21"),
            Student("Игорь Шевелев", "ИКБО-25-21"),
            Student("Эрик картман", "ИКБО-15-99"),
            Student("Лионеля Месси", "ИКБО-1-11"),

        )
        //setContentView(R.layout.activity_main)
        setContent {
            MyApp(students, applicationContext)
        }
    }
}