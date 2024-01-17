package com.yash.messiahwamr

//import com.yash.messiah.R
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.*
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.yash.messiahwamr.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity(),ActivityCompat.OnRequestPermissionsResultCallback {
//        var myFolder= File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah${File.separator}Deleted Images&Videos")
//        var myFolder2= File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah${File.separator}Downloaded Status")
//        val observer = GalleryObserver(Handler(Looper.getMainLooper()),this)
//        val resolver = this.contentResolver


    lateinit var notificationIntent:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title="Messiah"
        var myFolder= File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos")
        var myFolder2= File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}Downloaded Status")
        var myFolder3=File(getFilesDir()?.absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos")
        println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+myFolder2.absolutePath)
        println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+myFolder2.path)
//        resolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, observer)


//        val permission = ContextCompat.checkSelfPermission(
//            this.applicationContext,
//            Manifest.permission.READ_EXTERNAL_STORAGE
//        )
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                1
//            )
//        } else {
//            // Register the ContentObserver to observe changes to the WhatsApp images folder
//            this.contentResolver.registerContentObserver(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                true,
//                observer
//            )
//        }
//        resolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, observer)

        if(!myFolder.exists()){
            var d= myFolder.mkdirs();

        }


        ////////////////////////


//        getFolderPermission()

        //////////////////////////

        if(!myFolder2.exists()){
            var d= myFolder2.mkdirs();
            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
        }
        if(!myFolder3.exists()){
            var d= myFolder3.mkdirs();
            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
        }
        println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+myFolder3.exists())

//        if(!myFolder.exists()){
//            var d= myFolder.mkdirs();
//            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
//        }
//        if(!myFolder2.exists()){
//            var d= myFolder2.mkdirs();
//            println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
//        }
//        var targetpath=Environment.getExternalStorageDirectory().absolutePath+"/WhatsApp/Media/WhatsApp Images"
//        var targetdir=File(targetpath)
//        var fil=targetdir.listFiles()
//        for(i in 0..fil.size-1) {
//            if(fil[i].isDirectory){
//                var file = fil[i]
//                var path = file.absolutePath
//                var name = file.name
//
//                println("YEEYEYEYEOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
//                println(file)
//                println(path)
//                println(name)
//                println()
//
//
//            }
//        }

        println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW")

//        var f=File(Environment.getExternalStorageDirectory(), "CHALBeninkalaldldya")
//        if (!f.exists()) {
//            f.mkdirs();
//        }

//        val mediaObserverService = Intent(this, MediaObservingService::class.java)
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
        var mediaIntent=Intent(this, MediaObservingService::class.java)
        if(!isMyServiceRunning(mediaIntent::class.java)){
            startService(mediaIntent)

        }
//        }

//        medObsSwitch.isChecked = isServiceRunning(MediaObserverService::class.java)
//        medObsSwitch.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                startService(mediaObserverService)
//                Toast.makeText(applicationContext, getString(R.string.started), Toast.LENGTH_SHORT).show()
//            }
//            else {
//                stopService(mediaObserverService)
//                Toast.makeText(applicationContext, getString(R.string.stopped), Toast.LENGTH_SHORT).show()
//            }
//        }
//        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
//        startActivity(intent)
        notificationIntent=Intent(this, CustomNotificationListenerService::class.java)
        if(!isMyServiceRunning(notificationIntent.javaClass) ){
            startService(notificationIntent)

        }

        var viewPagerAdapter= com.yash.messiahwamr.CustomFragmentPageAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        viewPager.adapter=viewPagerAdapter


        viewPager.setOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))


        viewPagerAdapter.addFragment(chatHome(),"m")

        viewPagerAdapter.addFragment(HomeFragment2(),"p")

        viewPagerAdapter.addFragment(statusHome(),"s")
        viewPagerAdapter.notifyDataSetChanged()


        tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem=tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })




    }
    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                Log.i("isMyServiceRunning?", true.toString() + "")
                return true
            }
        }
        Log.i("isMyServiceRunning?", false.toString() + "")
        return false
    }

    override fun onDestroy() {
        stopService(notificationIntent)
        super.onDestroy()
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun getFolderPermission(){
        var storageManager=application?.getSystemService(Context.STORAGE_SERVICE) as StorageManager
//            var intent=Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply{
//                putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(Environment.getExternalStorageDirectory().absolutePath+"/Android/media/com.whatsapp/WhatsApp/Media"))
//            }
        var intent=storageManager.primaryStorageVolume.createOpenDocumentTreeIntent()
        var targetDirectory="Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses"
//        var targetDirectory2="Android/media/com.whatsapp/WhatsApp/Media"
        var uri=intent.getParcelableExtra<Uri>("android.provider.extra.INITIAL_URI")as Uri

        var scheme=uri.toString()
        scheme=scheme.replace("/root/","/tree/")
        scheme+="%3A$targetDirectory"
        uri=Uri.parse(scheme)

        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,uri)
        intent.putExtra("android.content.extra.SHOW_ADVANCED",true)
        startActivityForResult(intent,1234)
    }


//        fun permission(){
//            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
//                if(!myFolder.exists()){
//                    var d= myFolder.mkdirs();
//                    println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
//                }
//                if(!myFolder2.exists()){
//                    var d= myFolder2.mkdirs();
//                    println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
//                }
//
//            }
//            else{
//                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
//            }
//        }
//
//        override fun onRequestPermissionsResult(
//            requestCode: Int,
//            permissions: Array<out String>,
//            grantResults: IntArray
//        ) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//            if(requestCode==1){
//                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    if(!myFolder.exists()){
//                        var d= myFolder.mkdirs();
//                        println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
//                    }
//                    if(!myFolder2.exists()){
//                        var d= myFolder2.mkdirs();
//                        println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+d)
//                    }
//
//                }
//
//            }
//
//
//        }

//        override fun onRequestPermissionsResult(
//            requestCode: Int,
//            permissions: Array<out String>,
//            grantResults: IntArray
//        ) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//            // Register the ContentObserver to observe changes to the WhatsApp images folder
//            this.contentResolver.registerContentObserver(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                true,
//                observer
//            )
//
//        }
}
