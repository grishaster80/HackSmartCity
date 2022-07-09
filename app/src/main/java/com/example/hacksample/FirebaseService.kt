package com.example.hacksample

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.e("@@@",token)
        super.onNewToken(token)
    }
}