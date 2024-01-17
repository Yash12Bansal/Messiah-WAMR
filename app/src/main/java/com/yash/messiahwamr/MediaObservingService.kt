package com.yash.messiahwamr

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MediaObservingService: Service() {

//    lateinit var mediaObserver:HomeFragment2.MediaObserving

    var mediaObserver= HomeFragment2().MediaObserving(this)
//    lateinit var observer:HomeFragment2.WhatsAppImagesObserver
    val documentsUri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
    val selection = "${MediaStore.Files.FileColumns.RELATIVE_PATH} like ?"
    val selectionArgs = arrayOf("%WhatsApp/Media/WhatsApp Images%")

    private val mediaObserver2 = HomeFragment2().MediaObserving2(this)
    private val mediaObserver3 = HomeFragment2().MediaObserving3(this)
    private val mediaObserver4 = HomeFragment2().MediaObserving4(this)
    lateinit var app:Context
//    val observer = GalleryObserver(Handler(Looper.getMainLooper()),this.applicationContext)
//    val resolver = this.contentResolver
    private fun onPhotoAdded(uri: Uri) {
        // Handle the photo added event
        Log.e("PhotoAdded", "New photo added: $uri")
    }
//    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
//    app=this.application
//     observer= HomeFragment2().WhatsAppImagesObserver(app,Handler(),::onPhotoAdded)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
    {


//        var treeUri = Uri.parse(uriString)
//        val treeUri = DocumentsContract.buildTreeDocumentUri(
//            "com.whatsapp",
//            "Media"
//        )
//        val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//        this.contentResolver.takePersistableUriPermission(treeUri, takeFlags)

//        mediaObserver=HomeFragment2().MediaObserving(this,treeUri)


    }
        println("oncererereeeeeeeeeeeeeeeeeeeeeeeeewwwwwwwwwwwwwwww")
//    mediaObserver.startWatching()

    //
//        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java)
//            .let { notificationIntent ->
//                PendingIntent.getActivity(this, 0, notificationIntent, 0)
//            }
//        val chan = NotificationChannel("mediaObserver",
//            "My Foreground Service",
//            NotificationManager.IMPORTANCE_LOW)
////        chan.lightColor = Color.BLUE
////        chan.lockscreenVisibility = Notification.VISIBILITY_SECRET
//
//        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)!!
//        manager!!.createNotificationChannel(chan)
//
//        val notification: Notification = NotificationCompat.Builder(this,
//            "mediaObserver")
//            .setContentTitle("Media Observer")
//            .setChannelId("mediaObserver")
//            .setContentText("Watching for new images")
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentIntent(pendingIntent)
//            .setTicker("Watching for new images")
//            .build()
//
//        startForeground(1337, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("onstarcommmasnsnsns")
        mediaObserver.startWatching()
        mediaObserver2.startWatching()
        mediaObserver3.startWatching()
        mediaObserver4.startWatching()

        HomeFragment2().observeWhatsAppDocuments()
        var contentObserver: ContentObserver? = null

        contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean, uri: Uri?) {
                println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+uri)
            }
        }
//        contentResolver?.registerContentObserver(documentsUri, true, contentObserver!!)
//        contentResolver?.query(documentsUri, null, selection, selectionArgs, null)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {

        super.onDestroy()
        println("ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt")
        mediaObserver.stopWatching()
        mediaObserver2.stopWatching()
        mediaObserver3.stopWatching()
        mediaObserver4.stopWatching()
        val broadcastIntent = Intent(this, NotificationListenerRestartBroadcastReceiver::class.java)

        sendBroadcast(broadcastIntent)

    }
}
