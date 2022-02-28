package com.example.dummycardview

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import java.security.AccessController.getContext
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    internal lateinit var fDatabase: FirebaseDatabase

    @Inject
    internal lateinit var fStorage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initializeCard()
//        var testButton: Button = findViewById(R.id.btn_test)
//        var messageText: TextView= findViewById(R.id.txt_message)
        var celebrateButton: Button = findViewById(R.id.btn_celeb)
        val animationView: LottieAnimationView = findViewById(R.id.anim_celeb)
        var dataMap: Map<String, String>
        var animUrl: String  = ""

        fDatabase.reference.child("users/animations/celebration")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                     animUrl = snapshot.value.toString()

                }

                override fun onCancelled(error: DatabaseError) {
                    //TODO("Not yet implemented")
                }
            })
         celebrateButton.setOnClickListener {
             if(!animUrl.isNullOrEmpty()) {
                 animationView.setAnimationFromUrl(animUrl)
                 animationView.playAnimation()
             }
             val storageReference =
                 fStorage.reference.child("55861-confetti.json")
             storageReference.downloadUrl.addOnSuccessListener { uri: Uri ->
                 val url = uri.toString()

//                 downloadFile(
//                     this,
//                     "your file name",
//                     "file extension",
//                     DIRECTORY_DOWNLOADS,
//                     url
//                 )

             }.addOnFailureListener { e: Exception ->

             }
         }


//        testButton.setOnClickListener {
//
//            FirebaseInAppMessaging.getInstance().triggerEvent("on_foreground")
//        }
//        val listener = MyClickListener()
//        FirebaseInAppMessaging.getInstance().addClickListener(listener)


//        FirebaseInAppMessaging.getInstance().addClickListener { inAppMessage, action ->
//            dataMap = inAppMessage.data as Map<String, String>
//            messageText.text = dataMap.entries.toString()
//            Log.d(TAG, "dataMesss: ${dataMap.entries}")
//        }
    }
    private fun downloadFile(
        context: Context,
        fileName: String,
        fileExtension: String,
        destinationDirectory: String,
        url: String
    ) {
        val downloadmanager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(
            context,
            destinationDirectory,
            fileName + fileExtension
        )
        downloadmanager.enqueue(request)
    }
//    fun initializeCard() {
//        var webView: WebView = findViewById(R.id.webView)
//
//        val cardView: CardView = findViewById(R.id.cardView)
//        webView.webViewClient = object : WebViewClient() {
//
//            override fun onPageFinished(view: WebView, url: String?) {
//                view.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)")
//            }
//        }
//        webView.settings.javaScriptEnabled = true
//        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//        webView.settings.domStorageEnabled = true
//
//        webView.loadUrl("https://www.google.com")
//
//
//
//
//        val heightListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                //val post = dataSnapshot.getValue<Post>()
//                // ...
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//
//        fDatabase.reference.addValueEventListener(heightListener)
//        fDatabase.reference.child("users").child("ageomes").child("card").child("height_px")
//    }
}
