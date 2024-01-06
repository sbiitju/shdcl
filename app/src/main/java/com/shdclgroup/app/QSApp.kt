package com.shdclgroup.app


import android.app.Application
import com.google.firebase.FirebaseApp
import com.shdclgroup.app.core.util.Prefs
import dagger.hilt.android.HiltAndroidApp

val prefs: Prefs by lazy {
    QSApp.prefs!!
}

@HiltAndroidApp
class QSApp : Application() {
    companion object {
        var prefs: Prefs? = null
        lateinit var instance: QSApp
            private set

    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = Prefs(applicationContext)
//        initLogger()
        FirebaseApp.initializeApp(this)


        // Handle the received message here
        // You can extract the notification data and show a notification to the user
        // using the NotificationManager system service
    }
}

