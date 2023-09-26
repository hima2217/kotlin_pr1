package com.example.kotlin_pr1.UI.View

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay


class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        Log.d("doWork", "Началось выполнение работы")
        delay(5000)
        return Result.success()
    }
}