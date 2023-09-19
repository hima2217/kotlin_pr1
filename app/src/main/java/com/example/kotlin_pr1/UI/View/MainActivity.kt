package com.example.kotlin_pr1.UI.View


import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.hardware.SensorPrivacyManager.Sensors.CAMERA
import android.media.MediaRecorder.VideoSource.CAMERA
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_pr1.R
import com.example.kotlin_pr1.UI.StateHolder.ViewModel.MyViewModel
import com.example.kotlin_pr1.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH:mm:ss"
        val REQUIRED_PERMISSIONS =
            mutableListOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.data.observe(this, Observer { data ->
            // обновление UI с новыми данными
        })
    }
}