package com.example.kotlin_pr1.UI.View

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "My Channel"
            val channelDescription = "Описание вашего канала уведомлений"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channel_id", channelName, importance).apply {
                description = channelDescription
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

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