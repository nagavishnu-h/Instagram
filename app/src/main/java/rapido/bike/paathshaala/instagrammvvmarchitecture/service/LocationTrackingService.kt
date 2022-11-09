package rapido.bike.paathshaala.instagrammvvmarchitecture.service

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import rapido.bike.paathshaala.instagrammvvmarchitecture.R
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.MainActivity
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.LocationTracker

class LocationTrackingService : Service() {
    private val channelId = "Location Service"
    override fun onCreate() {
        super.onCreate()
        LocationTracker.createLocationRequest(applicationContext)
        LocationTracker.getLastLocation(applicationContext)
        if (Build.VERSION.SDK_INT >= 26) {
            val channel =
                NotificationChannel(
                    channelId,
                    "Location Service",
                    NotificationManager.IMPORTANCE_DEFAULT
                )

            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        val notification: Notification = NotificationCompat.Builder(this, "Location Service")
            .setContentTitle(getText(R.string.notification_title))
            .setContentText(
                getText(
                    R.string.notification_message
                )
            )
            .setOngoing(true)
            .setSmallIcon(R.drawable.comment)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationTracker.stopLocationUpdates()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}