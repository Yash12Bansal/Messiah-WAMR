package com.yash.messiahwamr

import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
//import com.yash.messiah.R
import java.io.File

class Videoo : AppCompatActivity() {
    lateinit var download: ImageView
    //    lateinit var mychat: ImageView
    lateinit var share: ImageView
    lateinit var nparticularvideo: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoo)

        nparticularvideo=findViewById(R.id.particularvideo)
        share=findViewById(R.id.share)
//        mychat=findViewById(R.id.mychatapp)
        download=findViewById(R.id.download)

        var intent=getIntent()

        var destpath=intent.getStringExtra("DEST_PATH_VIDEO")

        var file=intent.getStringExtra("FILE_VIDEO")
        var uri = intent.getStringExtra("URI_VIDEO")
        var name=intent.getStringExtra("FILENAME_VIDEO")

        var destpath2= File(destpath)
        var file1= File(file)

        var mediaController= MediaController(this)
        mediaController.setAnchorView(nparticularvideo)
        var uri1= Uri.parse(uri)
        nparticularvideo.setMediaController(mediaController)
        nparticularvideo.setVideoURI(uri1)
        nparticularvideo.requestFocus()
        nparticularvideo.start()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        share.setOnClickListener({
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri1)
                type = "video/mp4"
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
            MediaScannerConnection.scanFile(applicationContext, arrayOf(destpath+name), arrayOf("*/*"),object: MediaScannerConnection.MediaScannerConnectionClient{
                override fun onScanCompleted(path: String?, uri: Uri?) {
                }
                override fun onMediaScannerConnected() {
                }
            })

            Toast.makeText(this,"Video saved to GALLERY successfully", Toast.LENGTH_LONG).show()
        })



    }
}