package com.yash.messiahwamr

import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
//import com.yash.messiah.R
import java.io.File
import java.lang.Exception

class Picturee : AppCompatActivity() {
    lateinit var download: ImageView
    lateinit var share: ImageView
    lateinit var nparticularimage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picturee)
        nparticularimage=findViewById(R.id.particularimage)
        share=findViewById(R.id.share)
        download=findViewById(R.id.download)

        var intent=getIntent()

        var destpath=intent.getStringExtra("DEST_PATH_PICTURE")

        var file=intent.getStringExtra("FILE_PICTURE")
        var uri = intent.getStringExtra("URI_PICTURE")
        var name=intent.getStringExtra("FILENAME_PICTURE")


        var uri1= Uri.parse(uri)
        var destpath2= File(destpath)
        var file1= File(file)
        Glide.with(applicationContext).load(uri).into(nparticularimage)
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        share.setOnClickListener({
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri1)
                type = "image/*"
            }
            startActivity(Intent.createChooser(shareIntent, null))

        })

        download.setOnClickListener({
            try{
                org.apache.commons.io.FileUtils.copyFileToDirectory(file1,destpath2)


            }
            catch (e: Exception){
                e.printStackTrace()
            }
            MediaScannerConnection.scanFile(applicationContext, arrayOf((destpath+name).toString()), arrayOf("*/*"),object: MediaScannerConnection.MediaScannerConnectionClient{
                override fun onScanCompleted(path: String?, uri: Uri?) {
                }

                override fun onMediaScannerConnected() {
                }


            })
            Toast.makeText(this,"Picture saved to GALLERY successfully", Toast.LENGTH_LONG).show()

        })



    }
}