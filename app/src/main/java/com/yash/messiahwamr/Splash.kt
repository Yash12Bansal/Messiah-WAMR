package com.yash.messiahwamr

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.Settings
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
//import com.yash.messiah.R


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        println("dfffffffffffffffffffffffffffffffxsndsmdsndsmdsd,smdmdnsnds"+
//                Settings.Secure.getString(this@Splash.getContentResolver(),"enabled_notification_listeners").contains(getApplicationContext().getPackageName())
//        )

        Handler().postDelayed(object : Runnable{
            override fun run(){
                val cn = ComponentName(this@Splash, CustomNotificationListenerService::class.java)
                val flat = Settings.Secure.getString(
                    this@Splash.getContentResolver(),
                    "enabled_notification_listeners"
                )
                val enabled = flat != null && flat.contains(cn.flattenToString())
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                    if(!enabled || !Environment.isExternalStorageManager()){
                        var intent= Intent(applicationContext,Permissions::class.java)
                        startActivity(intent)
                        finish()

                    }
                    else{
                        var intent= Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
                else{
                    if (
                        !enabled ||
                        (ActivityCompat.checkSelfPermission(this@Splash,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    ) {
                        var intent= Intent(applicationContext,Permissions::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        var intent= Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }

            }
        }, 800)

    }
}