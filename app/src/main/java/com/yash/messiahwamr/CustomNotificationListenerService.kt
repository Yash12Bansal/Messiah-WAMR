package com.yash.messiahwamr

import android.annotation.SuppressLint
import android.app.*
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.yash.messiahwamr.models.chatHomeDAO
import com.yash.messiahwamr.models.chatHomeModel
import com.yash.messiahwamr.models.chatsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.util.*


var MSG_NOTIFICATION="msg_received"

class CustomNotificationListenerService: NotificationListenerService() {
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onNotificationPosted (sbn: StatusBarNotification) {

        var chatHomeDao: chatHomeDAO = chatsDatabase.getDatabase(Application()).chatHomeDao()

        super.onNotificationPosted(sbn)
        if(sbn.packageName!="com.whatsapp" || sbn.tag==null){
            return
        }

        var n:Notification=sbn.notification

//        var aaa=getActiveNotifications()
//
//        for(i in 0.. aaa.size-1){
//            println(aaa[i].notification.extras.getCharSequence("android.text").toString())
//        }
//        println(n.number)
//        println(n.visibility)
        var a=sbn.notification
        cancelNotification(sbn.key)
//        println(sbn.postTime)
//        println(a.`when`)

//
//        var aa=sbn.key
//        var arr= arrayOf(aa)
//
//        var ar=getActiveNotifications(arr)



//        println("adffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffd")
//        println(a.extras.getString(Notification.EXTRA_TITLE))
//        println(a.extras.getString(Notification.EXTRA_MESSAGES))
//        println(a.extras.getString(Notification.EXTRA_MESSAGING_PERSON))
//        println(a.extras.getString(Notification.EXTRA_SUB_TEXT))
//        println(a.extras.getString(Notification.CATEGORY_MESSAGE))
//        println(a.extras.getString(Notification.EXTRA_BIG_TEXT))


//        for(i in 0..x.size-1){
//            println(x[i])
//        }
//        println(x[0])
//        println(x[x.size-1])
//        println(a.extras.getString("android.title"))
//        println(a.extras.getCharSequence("android.text")).toString()
//        println(sbn.key).toString()

//        println(sbn.tag).toString()
//        println(sbn.groupKey)
//        println(sbn.user)
//        println(sbn.postTime)
//        println(sbn)
//        k2UJuqNMvsLvMB/rJgrGsos8qzUpMjfgop6FdNOpG5k=
//        eksTBVG5s4DqIjus4KxO2K/cNPqOQIIhNHJgJvwO8mk=
//        println(sbn.notification.tickerText.toString())
        println(a.extras.getCharSequence("android.text").toString())
        println("\n")
        var name=a.extras.getString("android.title")
        var msg=a.extras.getCharSequence("android.text").toString()
        var tag=sbn.tag

        var time=sbn.postTime.toLong()
        var d=sbn.isAppGroup
        var dd=sbn.isGroup
        var ddd=a.group
        var dddd=a.extras.get("android.bigText")
        var isgroup=a.extras.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)



        println("a.extras.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE)ddddddddddddddddd")

        println(a.extras.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION))

        println(a.extras.getCharSequence("android.title"))
//        println(a.extras.getCharSequence(NotificationCompat.EXTRA_MESSAGING_STYLE_USER))
        println(a.extras.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE))
        println(a.extras.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE))

        var group=a.extras.getString(NotificationCompat.EXTRA_CONVERSATION_TITLE)

//        println(a.extras.getCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT))
//        println(a.extras.getCharSequence(Notification.EXTRA_MESSAGING_PERSON))
//        println(a.extras.getCharSequence(Notification.CATEGORY_MESSAGE))
//        var ss:Array<Parcelable> = a.extras.get(Notification.EXTRA_MESSAGES) as Array<Parcelable>
//        println(ss[0])
//        println(a.extras.getCharSequence(Notification.EXTRA_INFO_TEXT))
//        println(a.extras.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG))



//        println(dddd)
//        println(d)
//        println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+  msg +"DFDF   ")

//        println("\uD83D\uDCF7 Photo")
//        println("\uD83C\uDFA5 Video")
//        println("\uD83D\uDCF7 Photo" )
//
//        var intent=Intent().apply {
//            action = MSG_NOTIFICATION
//            putExtra("name",a.extras.getString("android.title"))
//            putExtra("msg",a.extras.getCharSequence("android.text").toString())
//            putExtra("tag",sbn.tag)
//            putExtra("time",sbn.postTime.toString())
//        }
//
        CoroutineScope(Dispatchers.IO).launch {
            chatHomeDao.addMessenger(chatHomeModel(0,tag?:"Unknown",1,name?:"Unknown",msg?:"Unknown",time?:0,
                isgroup as Boolean,group?:"Unknown"
            ))
        }

//        if (a.extras.containsKey(Notification.e)) {
//            // this bitmap contain the picture attachment
//            val bmp = a.extras.get(Notification.EXTRA_PICTURE)
//        }
///////////////////////////////////////////////////////////////FJKFDJKFDKFKDJFKFKJDFJKFJKF////////////////////
//        Handler(Looper.getMainLooper()).postDelayed(
//            {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    if (msg.contains("\uD83D\uDCF7 Photo")) {
//                        var sharedPref=  getSharedPreferences("pref", Context.MODE_PRIVATE)
//                        var uriString=sharedPref?.getString("MEDIA_URI",null)
//
//                        var treeUri= Uri.parse(uriString)
////            requestPermission()
//                        if(treeUri!=null){
//                            var nu= Uri.parse((treeUri.toString())+"%2FWhatsApp Images")
////                var uu=DocumentsContract.buildDocumentUriUsingTree(treeUri,DocumentsContract.getDocumentId(treeUri))
////                println(uu)
//
//                            var myUri= DocumentsContract.buildChildDocumentsUriUsingTree(
//                                treeUri,
//                                DocumentsContract.getTreeDocumentId(treeUri))
//                            val mydirNodes: MutableList<Uri> = LinkedList()
//
//                            val dirNodes: MutableList<Uri> = LinkedList()
//                            var mimeArray:MutableList<String> = mutableListOf()
//                            var nameArray:MutableList<String> = mutableListOf()
//                            var dateArray:MutableList<Long> = mutableListOf()
//
//                            dirNodes.add(myUri)
//                            while (!dirNodes.isEmpty()) {
//                                myUri = dirNodes.removeAt(0) // get the item from top
////                    println("GGGGGGGGGGG"+myUri)
////                    println("GGGGGGGGGGG"+myUri.lastPathSegment)
//
////                    if(myUri.toString().contains(".Statuses")){
//                                val c: Cursor? = contentResolver.query(
//                                    myUri,
//                                    arrayOf(
//                                        DocumentsContract.Document.COLUMN_DOCUMENT_ID,
//                                        DocumentsContract.Document.COLUMN_DISPLAY_NAME,
//                                        DocumentsContract.Document.COLUMN_LAST_MODIFIED,
//                                        DocumentsContract.Document.COLUMN_MIME_TYPE
//                                    ),
//                                    null,
//                                    null,
//                                    null
//                                )
//                                try {
//                                    if (c != null) {
//                                        while (c.moveToNext()) {
//                                            val docId: String = c.getString(0)
//                                            val name: String = c.getString(1)
//                                            var date=c.getString(2)
//                                            val mime: String = c.getString(3)
//
////                                    Log.d("TAG", "docId: $docId, name: $name, mime: $mime, date  $date")
//                                            var newNode =
//                                                DocumentsContract.buildChildDocumentsUriUsingTree(
//                                                    treeUri,
//                                                    docId
//                                                )
//                                            if (docId.contains("WhatsApp Images") && !docId.endsWith("WhatsApp Images") && !docId.endsWith(".nomedia") && mime!="vnd.android.document/directory"
//                                                && !docId.contains("Sent") && !docId.contains("Private")) {
//                                                mydirNodes.add(newNode)
//                                                mimeArray.add(mime)
//                                                nameArray.add(name)
//                                                dateArray.add(date.toLong())
//                                            }
//                                            dirNodes.add(newNode)
//                                        }
//                                        println(" ")
//                                    }
//                                }
//                                finally {
////                        closeQuietly(c)
//                                }
//                            }
//                            var maxi:Long=0
//                            var myFile:Int=0
//                            println("MOFFIIDEIREIRELEELELELELELELFDJLFDFDATE  " + dateArray.size)
//
//                            var mm =mutableListOf<Long>()
//                            for(i in dateArray){
//                                mm.add(i)
//                            }
//
//                            mm.sort()
//                            for(i in 0.. nameArray.size-1){
//                                println(""+dateArray[i]+" "+nameArray[i]+" "+mm[i])
//                            }
//
//                            for (i in 0..mydirNodes.size-1) {
//                                var n = mydirNodes[i].pathSegments
//                                var name=nameArray[i]
//                                var path = mydirNodes[i].path
//
//                                if(dateArray[i]>maxi){
//                                    maxi=dateArray[i]
//                                    myFile=i
//                                }
////                        fileslist.add(f)
//                                println("TT    LL " + i)
//
//                            }
//                            println("myfile "+myFile)
//                            println("myfile "+dateArray[myFile])
//                            println("myfile "+nameArray[myFile])
//                            println(System.currentTimeMillis())
//
//                            var f = ModelClass(mydirNodes[myFile!!].path!!, nameArray[myFile], mydirNodes[myFile],mimeArray[myFile])
//                            saveFile(f.uri,f.filename,f.mimeType!!)
//                        }
//                    }






///////////////////////////////////////fjfdfdhfhhDFHDFHJ
    //                    if(msg.contains("\uD83C\uDFA5 Video")){
//                        var sharedPref=  getSharedPreferences("pref", Context.MODE_PRIVATE)
//                        var uriString=sharedPref?.getString("MEDIA_URI",null)
//
//                        var treeUri= Uri.parse(uriString)
////            requestPermission()
//                        if(treeUri!=null){
//                            var nu= Uri.parse((treeUri.toString())+"%2FWhatsApp Video")
////                var uu=DocumentsContract.buildDocumentUriUsingTree(treeUri,DocumentsContract.getDocumentId(treeUri))
////                println(uu)
//
//                            var myUri= DocumentsContract.buildChildDocumentsUriUsingTree(
//                                treeUri,
//                                DocumentsContract.getTreeDocumentId(treeUri))
//                            val mydirNodes: MutableList<Uri> = LinkedList()
//
//                            val dirNodes: MutableList<Uri> = LinkedList()
//                            var mimeArray:MutableList<String> = mutableListOf()
//                            var nameArray:MutableList<String> = mutableListOf()
//                            var dateArray:MutableList<Long> = mutableListOf()
//
//                            dirNodes.add(myUri)
//                            while (!dirNodes.isEmpty()) {
//                                myUri = dirNodes.removeAt(0) // get the item from top
////                    println("GGGGGGGGGGG"+myUri)
////                    println("GGGGGGGGGGG"+myUri.lastPathSegment)
//
////                    if(myUri.toString().contains(".Statuses")){
//                                val c: Cursor? = contentResolver.query(
//                                    myUri,
//                                    arrayOf(
//                                        DocumentsContract.Document.COLUMN_DOCUMENT_ID,
//                                        DocumentsContract.Document.COLUMN_DISPLAY_NAME,
//                                        DocumentsContract.Document.COLUMN_LAST_MODIFIED,
//
//                                        DocumentsContract.Document.COLUMN_MIME_TYPE
//                                    ),
//                                    null,
//                                    null,
//                                    null
//                                )
//                                try {
//                                    if (c != null) {
//                                        while (c.moveToNext()) {
//                                            val docId: String = c.getString(0)
//                                            val name: String = c.getString(1)
//                                            var date=c.getString(2)
//                                            val mime: String = c.getString(3)
//
////                                    Log.d("TAG", "docId: $docId, name: $name, mime: $mime")
//                                            var newNode =
//                                                DocumentsContract.buildChildDocumentsUriUsingTree(
//                                                    treeUri,
//                                                    docId
//                                                )
//                                            if (docId.contains("WhatsApp Video") && !docId.endsWith("WhatsApp Video") && !docId.endsWith(".nomedia") && mime!="vnd.android.document/directory"
//                                                && !docId.contains("Sent") && !docId.contains("Private")) {
//                                                mydirNodes.add(newNode)
//                                                mimeArray.add(mime)
//                                                nameArray.add(name)
//                                                dateArray.add(date.toLong())
//
//                                            }
//
//                                            dirNodes.add(newNode)
//                                        }
//                                        println(" ")
//                                    }
//                                }
//                                finally {
////                        closeQuietly(c)
//                                }
//                            }
//                            var maxi:Long=0
//                            var myFile:Int=0
//
//                            println("MOFFIIDEIREIRELEELELELELELELFDJLFDFDATE  " + dateArray.size)
//
//                            var mm =mutableListOf<Long>()
//                            for(i in dateArray){
//                                mm.add(i)
//                            }
//
//                            mm.sort()
//                            for(i in 0.. nameArray.size-1){
//                                println(""+dateArray[i]+" "+nameArray[i]+" "+mm[i])
//                            }
//                            for (i in 0..mydirNodes.size-1) {
//                                var n = mydirNodes[i].pathSegments
//                                var name=nameArray[i]
//                                var path = mydirNodes[i].path
//
//                                if(dateArray[i]>maxi){
//                                    maxi=dateArray[i]
//                                    myFile=i
//                                }
////                        fileslist.add(f)
//                                println("TT    LL " + i)
//
//                            }
//                            println("myfile "+myFile)
//                            println("myfile "+dateArray[myFile])
//                            println("myfile "+nameArray[myFile])
//                            println(System.currentTimeMillis())
//
//                            var f = ModelClass(mydirNodes[myFile!!].path!!, nameArray[myFile], mydirNodes[myFile],mimeArray[myFile])
//                            saveFile(f.uri,f.filename,f.mimeType!!)
//                        }
//                    }
        ////////////////////////////////////DFKDLFKJLDFKJLDFKJLFJKLKFJL//////////////////////////////////
//                }
//            },
//            2000 // value in milliseconds
//        )
//////////////////////////////DFKLDFKDFKJKFDJLF

//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        val broadcastIntent = Intent(this, NotificationListenerRestartBroadcastReceiver::class.java)

        sendBroadcast(broadcastIntent)
    }

    override fun getCurrentRanking(): RankingMap {
        return super.getCurrentRanking()
    }
    fun saveFile( sourceUri:Uri, fileName:String, mimeType:String){

        var  values =  ContentValues();
        lateinit var destinationUri:Uri;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
            var uu= File(this.getFilesDir(),"My Media")
            if(!uu.exists()){
                uu.mkdirs()
            }
            val file = File(uu, fileName)
            val inputStream = this.contentResolver.openInputStream(sourceUri)
            val outputStream = FileOutputStream(file)
            inputStream.use { input ->
                outputStream.use { output ->
                    input?.copyTo(output)
                }
            }
        }
    }


    override fun getActiveNotifications(keys: Array<out String>?): Array<StatusBarNotification> {

        var a= keys?.get(0)

        return super.getActiveNotifications(keys)


    }
}