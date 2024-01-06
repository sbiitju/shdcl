package com.shdclgroup.app.core.util

import com.google.firebase.messaging.FirebaseMessaging
import com.shdclgroup.app.prefs

abstract class Utils {
    companion object {
        fun getFCMToken(onSuccess: (String) -> Unit) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@addOnCompleteListener
                }

                // Get the FCM token
                val token = task.result
                onSuccess.invoke(token)
            }
        }

        fun updateUser(user: User) {
            prefs.put("JWT", user.jwtToken)
            prefs.put("USER_ID", user._id)
            prefs.put("FCM_TOKEN", user.fcmToken)
            prefs.put("NAME", user.name)
            prefs.put("EMAIL", user.email)
            prefs.put("ROLE", user.role)
        }

        val role = prefs.get<String>("ROLE")
        val name = prefs.get<String>("NAME").toString()
        val email = prefs.get<String>("EMAIL").toString()
        val userID = prefs.get<String>("USER_ID").toString()


    }
}


data class User(
    val jwtToken: String,
    val _id: String,
    val fcmToken: String,
    val name: String,
    val email: String,
    val role: String
)
