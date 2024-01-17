package com.yash.messiahwamr

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.*
//import com.yash.messiah.R
import com.yash.messiahwamr.databinding.ActivityEachVideoBinding
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileOutputStream

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class EachVideo : AppCompatActivity() {
    lateinit var download: ImageView
    //    lateinit var mychat: ImageView
    lateinit var share: ImageView
    lateinit var nparticularvideo: VideoView

    private lateinit var binding:ActivityEachVideoBinding
    private lateinit var fullscreenContent: RelativeLayout
    private lateinit var fullscreenContentControls: LinearLayout
    private val hideHandler = Handler(Looper.myLooper()!!)

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar
        if (Build.VERSION.SDK_INT >= 30) {
            fullscreenContent.windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            fullscreenContent.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
//        supportActionBar?.show()
        fullscreenContentControls.visibility = View.VISIBLE
    }
    private var isFullscreen: Boolean = false

    private val hideRunnable = Runnable { hide() }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val delayHideTouchListener = View.OnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS)
            }
            MotionEvent.ACTION_UP -> view.performClick()
            else -> {
            }
        }
        false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEachVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        isFullscreen = true

        // Set up the user interaction to manually show or hide the system UI.
        fullscreenContent = binding.complete
        fullscreenContent.setOnClickListener { toggle() }

        fullscreenContentControls = binding.components

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        binding.share.setOnTouchListener(delayHideTouchListener)
//        binding.download.setOnTouchListener(delayHideTouchListener)
        nparticularvideo=findViewById(R.id.particularvideo)
        share=findViewById(R.id.share)
//        mychat=findViewById(R.id.mychatapp)
        download=findViewById(R.id.download)

        var intent=getIntent()

        var destpath=intent.getStringExtra("DEST_PATH_VIDEO")

        var file=intent.getStringExtra("FILE_VIDEO")
        var uri = intent.getStringExtra("URI_VIDEO")
        var name=intent.getStringExtra("FILENAME_VIDEO")
        var mime=intent.getStringExtra("MIME_TYPE")


        var destpath2= File(destpath)
        var file1= File(file)

        var mediaController= MediaController(this)
        mediaController.setAnchorView(nparticularvideo)
//        uri=uri?.subSequence(0,(uri?.length?:0)-9).toString()

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
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
//
//                    saveFile(uri1, name!!, mime!!)
//                }
//                else{
                    org.apache.commons.io.FileUtils.copyFileToDirectory(file1,destpath2)
                MediaScannerConnection.scanFile(applicationContext, arrayOf(destpath+name), arrayOf("*/*"),object: MediaScannerConnection.MediaScannerConnectionClient{
                    override fun onScanCompleted(path: String?, uri: Uri?) {
                    }

                    override fun onMediaScannerConnected() {
                    }


                })

//                }
                Toast.makeText(this, "The File has been saved!", Toast.LENGTH_SHORT).show();

            }
            catch (e: Exception){
                e.printStackTrace()
            }

            Toast.makeText(this,"Video saved to GALLERY successfully", Toast.LENGTH_LONG).show()
        })





    }

    fun saveFile( sourceUri:Uri, fileName:String, mimeType:String){

        var  values =  ContentValues();
        lateinit var destinationUri:Uri;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);

            if (fileName.endsWith(".mp4")){
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/WhatsApp Messiah/Downloaded Status");
                destinationUri = this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)!!;
            } else {
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/WhatsApp Messiah/Downloaded Status");
                destinationUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!;
            }

//            destinationUri=Uri.parse(filesDir ?.absolutePath+"WhatsApp Messiah${File.separator}Downloaded Status")
            var uu=File(this.getFilesDir(),"Downloaded Statuses")
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

//            var inputStream = this.getContentResolver().openInputStream(sourceUri);
//            var outputStream = this.getContentResolver().openOutputStream(destinationUri);
//
//            IOUtils.copy(inputStream, outputStream)

        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    private fun toggle() {
        if (isFullscreen) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreenContentControls.visibility = View.GONE
        isFullscreen = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
//        if (Build.VERSION.SDK_INT >= 30) {
//            fullscreenContent.windowInsetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//        } else {
//            fullscreenContent.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//        }
        isFullscreen = true

        // Schedule a runnable to display UI elements after a delay
        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}