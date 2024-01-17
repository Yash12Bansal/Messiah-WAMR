package com.yash.messiahwamr

//import com.yash.messiah.R

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.provider.Settings
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_permissions.*
import java.io.File


class Permissions : AppCompatActivity() ,ActivityCompat.OnRequestPermissionsResultCallback{

    lateinit var myFolder: File
    lateinit var myFolder2: File
    lateinit var myFolder3:File
    lateinit var myFolder4:File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        myFolder= File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos")
//        myFolder2= File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}Downloaded Status")
        myFolder2= File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah/Downloaded Statuses")

        myFolder3=File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos")
        if(!myFolder.exists()){
            var d= myFolder.mkdirs();
        }
        if(!myFolder2.exists()){
            var d= myFolder2.mkdirs();
            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
        }
        if(!myFolder3.exists()){
            var d= myFolder3.mkdirs();
            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
        }

        permission()
    }

    override fun onResume() {
        super.onResume()
        myFolder= File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos")
//        myFolder2= File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}Downloaded Status")
        myFolder2= File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah/Downloaded Statuses")

        myFolder3=File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos")
//        myFolder4=File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}Downloaded Status")
//
        println("eheeooddododddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+getExternalFilesDir(null)?.absolutePath)
        if(!myFolder.exists()){
            var d= myFolder.mkdirs();
        }
        if(!myFolder2.exists()){
            var d= myFolder2.mkdirs();
            println("EEEEWWWW")
            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
        }
        if(!myFolder3.exists()){
            var d= myFolder3.mkdirs();
            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
        }

        val cn = ComponentName(this, CustomNotificationListenerService::class.java)
        val flat = Settings.Secure.getString(
            this.getContentResolver(),
            "enabled_notification_listeners"
        )
        val enabled = flat != null && flat.contains(cn.flattenToString())
        println("5534897324834983748738473847398473894444444487777777777777777777777777777777383838383838")

        if (enabled)
        {
            p1.isClickable=false
            p1.setText("ALLOWED")
            p1.setBackgroundColor(Color.BLUE)
            p1.alpha=0.3F
            if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
                var i=Intent(this,MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
                finish()
            }
        }
        else {
            p1.setOnClickListener{
                var i=Intent(
                    "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                getApplicationContext().startActivity(i)

            }
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            if(!Environment.isExternalStorageManager()){
                p2.setOnClickListener {
                    requestPermission()
//                    getFolderPermission()
                }
            }
            else{
                p2.isClickable=false
                p2.setText("ALLOWED")
                p2.setBackgroundColor(Color.BLUE)
                p2.alpha=0.3F
                if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
                    var i=Intent(this,MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                    finish()
                }

            }



        }
        else{
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                p2.isClickable=false
                p2.setText("ALLOWED")
                p2.setBackgroundColor(Color.BLUE)
                p2.alpha=0.3F
                if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
                    var i=Intent(this,MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                    finish()
                }
            }
            else{
                p2.setOnClickListener{
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(WRITE_EXTERNAL_STORAGE),
                        10
                    )
                }
            }

        }


    }

//    @RequiresApi(Build.VERSION_CODES.Q)
    fun permission(){
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
        if(!Environment.isExternalStorageManager()){
            p2.setOnClickListener {
                requestPermission()
//                    getFolderPermission()
            }
        }
        else{
                p2.isClickable=false
                p2.setText("ALLOWED")
                p2.setBackgroundColor(Color.BLUE)
                p2.alpha=0.3F
                if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
                    var i=Intent(this,MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                    finish()
                }

        }
//        var uriList=contentResolver.persistedUriPermissions
//        var sharedPref=  getSharedPreferences("pref", Context.MODE_PRIVATE)
//        if(sharedPref.contains("MEDIA_URI")){
//            var uriString=sharedPref?.getString("MEDIA_URI",null)
//            var treeUri= Uri.parse(uriString)
//
//            println("NNNMMM")
//            var f=false
//            for(i in uriList){
//                var uri=i.uri
//                if(uri==treeUri){
//                    f=true
//                }
//            }
//            if(f){
//                p2.isClickable=false
//                p2.setText("ALLOWED")
//                p2.setBackgroundColor(Color.BLUE)
//                p2.alpha=0.3F
//                if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
//                    var i=Intent(this,MainActivity::class.java)
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    startActivity(i)
//                    finish()
//                }
//            }
//            else{
//                p2.setOnClickListener {
//                    requestPermission()
////                    getFolderPermission()
//                }
//
//            }
//
//        }
//        else{
//            p2.setOnClickListener {
//                requestPermission()
////                getFolderPermission()
//            }
//        }
//        if(uriList.contains(treeUri))
    }
    else{
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            if(!myFolder.exists()){
                var d= myFolder.mkdirs();
            }
            if(!myFolder2.exists()){
                var d= myFolder2.mkdirs();
                println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
            }
            if(!myFolder3.exists()){
                var d= myFolder3.mkdirs();
                println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
            }

            p2.isClickable=false
            p2.setText("ALLOWED")
            p2.setBackgroundColor(Color.BLUE)
            p2.alpha=0.3F
            if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
                var i=Intent(this,MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
                finish()
            }
        }
        else {
            p2.setOnClickListener {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                    println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM")
                    requestPermission()
//                    getFolderPermission()
                }
                else{
                    println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN")
                    ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
                }
            }
        }

    }

    }
    private fun requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data =
                    Uri.parse(String.format("package:%s", applicationContext.packageName))
                startActivityForResult(intent, 2296)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivityForResult(intent, 2296)
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(
                this,
                arrayOf(WRITE_EXTERNAL_STORAGE),
                10
            )
        }
    }
//    @RequiresApi(Build.VERSION_CODES.Q)
//    fun getFolderPermission(application: Application? =null){
//
//        var storageManager=this.getSystemService(Context.STORAGE_SERVICE) as StorageManager
////        var intent=Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply{
////            putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(Environment.getExternalStorageDirectory().absolutePath+"/Android/media/com.whatsapp/WhatsApp/Media"))
////        }
//        var intent=storageManager.primaryStorageVolume.createOpenDocumentTreeIntent()
//        var targetDirectory="Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia"
////        var targetDirectory2="Android/media/com.whatsapp/WhatsApp/Media"
//        var uri=intent.getParcelableExtra<Uri>("android.provider.extra.INITIAL_URI")as Uri
//
//        var scheme=uri.toString()
//        scheme=scheme.replace("/root/","/document/")
//        scheme+="%3A$targetDirectory"
//        uri=Uri.parse(scheme)
//
//        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,uri)
//        intent.putExtra("android.content.extra.SHOW_ADVANCED",true)
//        startActivityForResult(intent,1234)
//    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ")

        if (resultCode == RESULT_OK) {
            println("XXXXXNEOEOEOEOEOEOEOOWWOQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQLKLKLK")

            @RequiresApi(Build.VERSION_CODES.R)
            if(Environment.isExternalStorageManager()){
                if(!myFolder.exists()){
                    var d= myFolder.mkdirs();
                }
                if(!myFolder2.exists()){
                    var d= myFolder2.mkdirs();
                    println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
                }
                if(!myFolder3.exists()){
                    var d= myFolder3.mkdirs();
                    println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
                }

                println("NEOEOEOEOEOEOEOOWWOQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQLKLKLK")
                p2.isClickable=false
                p2.setText("ALLOWED")
                p2.setBackgroundColor(Color.BLUE)
                p2.alpha=0.3F
                if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
                    var i=Intent(this,MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                    finish()
                }


            }
//            var uuri: Uri? = null
//            var treeUri = data?.data
//            println("RR")
//
//            if(treeUri!=null){
//                contentResolver.takePersistableUriPermission(treeUri,Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            }
//            println(Uri.parse(treeUri.toString()))
//
//            var id = data?.identifier
//            var uriString=treeUri.toString()
//            if(uriString.endsWith("Media")){
//                var sharedPref=getSharedPreferences("pref",Context.MODE_PRIVATE)
//                with(sharedPref.edit()){
//                    putString("MEDIA_URI",uriString)
//                    commit()
//                }
//                p2.isClickable=false
//                p2.setText("ALLOWED")
//                p2.setBackgroundColor(Color.BLUE)
//                p2.alpha=0.3F
//                if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
//                    var i=Intent(this,MainActivity::class.java)
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    startActivity(i)
//                    finish()
//                }
//                println(Uri.parse(treeUri.toString()))
//
//            }
            else{
                println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDEEEEEEEEEEEEEEEEEEEEEEEEE")
                Snackbar.make(p2,"Allow access to Media folder",Snackbar.LENGTH_INDEFINITE).setAction("OK",{
//                    getFolderPermission()
                    requestPermission()
                }).show()
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==1){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                if(!myFolder.exists()){
                    var d= myFolder.mkdirs();
                    println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV11111111111"+d)
                }
                if(!myFolder2.exists()){
                    var d= myFolder2.mkdirs();
                    println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV222222222222"+d)
                }
                if(!myFolder3.exists()){
                    var d= myFolder3.mkdirs();
                    println(myFolder3.exists())
                    println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV33333333333"+d)
                }

                p2.isClickable=false
                p2.setText("ALLOWED")
                p2.setBackgroundColor(Color.BLUE)
                p2.alpha=0.3F
                if(p1.text=="ALLOWED" && p2.text=="ALLOWED"){
                    var i=Intent(this,MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                    finish()
                }
            }
            else{
                println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDEEEEEEEEEEEEEEEEEEEEEEEEE")
                Snackbar.make(p2,"Storage Permission Needed!!",Snackbar.LENGTH_INDEFINITE).setAction("OK",{
                    ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)

                }).show()
            }

        }


    }
}