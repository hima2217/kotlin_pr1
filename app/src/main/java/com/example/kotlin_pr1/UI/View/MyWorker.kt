package com.example.kotlin_pr1.UI.View

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.kotlin_pr1.R
import kotlinx.coroutines.delay


class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        try {
            // Здесь выполняется фоновая работа.
            // Например, можете добавить задержку в 5 секунд.
            Log.d("doWork", "Началось выполнение работы")
            //delay(5000)

            val notification = NotificationCompat.Builder(applicationContext, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Проверьте, что "ic_notification" - это правильное имя вашей иконки
                .setContentTitle("Ворк")
                .setContentText("Работает")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            // Отображаем уведомление
            val notificationManager = NotificationManagerCompat.from(applicationContext)
            notificationManager.notify(1, notification)

            return Result.success()
        } catch (e: Exception) {
            // Обработка ошибок и возврат Result.failure() в случае неудачи
            Log.e("doWork", "Произошла ошибка во время выполнения работы", e)
            return Result.failure()
        }
    }
}