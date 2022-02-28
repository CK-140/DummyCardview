package com.example.dummycardview

import com.google.firebase.inappmessaging.model.InAppMessage

import com.google.firebase.inappmessaging.FirebaseInAppMessagingClickListener
import com.google.firebase.inappmessaging.model.Action


class MyClickListener : FirebaseInAppMessagingClickListener {
    override fun messageClicked(inAppMessage: InAppMessage, action: Action) {
        // Determine which URL the user clicked
        val url: String? = action.getActionUrl()

        // Get data bundle for the inapp message
        val dataBundle: Map<*, *>? = inAppMessage.data

        // ...
    }
}