package com.yash.messiahwamr

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yash.messiahwamr.adapters.DownloadedStatusAdapter
import com.yash.messiahwamr.models.ModelClass
import org.apache.commons.io.IOUtils
import java.io.File

class DownloadedStatus : AppCompatActivity() {
    lateinit var adapter: DownloadedStatusAdapter
    var files:List<File> =emptyList()
    var fileslist:ArrayList<ModelClass> =arrayListOf()
    lateinit var recyclerview: RecyclerView
//    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloaded_status)
        recyclerview=findViewById(R.id.recyclerview)

        supportActionBar?.title="Downloaded Status"
        setuplayout()




    }
    private fun setuplayout() {

        fileslist?.clear()
        recyclerview.setHasFixedSize(true)
        var staggeredGridLayoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        recyclerview.layoutManager=staggeredGridLayoutManager
        println("helfldfdlkfjdlkfjdklfjdkfjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")


        adapter= DownloadedStatusAdapter(this,getData())
        println("helfldfdlkfjdlkfjdklfjdkfjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")
//        println(?.filename)
        println("dfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff       "+fileslist)
        println("dfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff       "+fileslist.size)

        recyclerview.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    fun getData(): ArrayList<ModelClass>?{
        lateinit var f:ModelClass

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {

            var file= File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah/Downloaded Statuses")
            if(file.listFiles()==null){
                files= emptyList()
            }
            else{

                for(i in 0..file.listFiles().toList().size-1){

                    var file=file.listFiles().toList()[i]
                    var path=file.absolutePath
                    var name=file.name

                    println(file)
                    println(path)
                    println(name)

                    var uri= Uri.fromFile(file)


                    f= ModelClass(path,name,uri)

                    if(!f.uri.toString().endsWith(".nomedia")){
                        fileslist.add(f)
                    }
                }

            }
//            val treeUri = DocumentsContract.buildTreeDocumentUri(
//                this.packageName,
//                "DCIM"
//            )
//            val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//            this.contentResolver.takePersistableUriPermission(treeUri, takeFlags)
//
//            val dcimFolderUri = DocumentsContract.buildDocumentUriUsingTree(treeUri, "DCIM")
//            var  values =  ContentValues();
//            lateinit var destinationUri:Uri;
//
////            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
////                values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
////                values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
//
////                if (fileName.endsWith(".mp4")){
//                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/WhatsApp Messiah/Downloaded Status");
//                    destinationUri = this@DownloadedStatus.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
////                }
////        else {
////                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/WhatsApp Messiah/Downloaded Status");
////                    destinationUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!;
////                }
//
////            destinationUri=Uri.parse(filesDir ?.absolutePath+"WhatsApp Messiah${File.separator}Downloaded Status")
////
////                var inputStream = this.getContentResolver().openInputStream(sourceUri);
////                var outputStream = this.getContentResolver().openOutputStream(destinationUri);
//
////                IOUtils.copy(inputStream, outputStream)
//
////            }
//
//            var ff=File(destinationUri.toString())
//            println(destinationUri)
//            for(i in 0..ff.listFiles().size-1){
//
//                var file=files[i]
//                var path=file.absolutePath
//                var name=file.name
//
//                println(file)
//                println(path)
//                println(name)
//
//                var uri= Uri.fromFile(file)
//
//
//                f= ModelClass(path,name,uri)
//
//                if(!f.uri.toString().endsWith(".nomedia")){
//                    fileslist.add(f)
//                }
//            }
//

        }

        else{
            var targetdir= File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah/Downloaded Statuses")
//        var targetdir=File(targetpath)
            println("QQQQQQQQQQQQQQQQQQQQQQQQQWQZZZZZZZZXXXXXXXXXXXX")
            println(targetdir.exists())
            println(targetdir.absolutePath)
            println(targetdir.listFiles().size)

            if(targetdir.listFiles()==null){
                files= emptyList()
            }
            else{
                files= targetdir.listFiles().toList()

            }
            for(i in 0..files.size-1){
                var file=files[i]
                var path=file.absolutePath
                var name=file.name

                println(file)
                println(path)
                println(name)

                var uri= Uri.fromFile(file)


                f= ModelClass(path,name,uri)

                if(!f.uri.toString().endsWith(".nomedia")){
                    fileslist.add(f)
                }

            }
            println("fddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")

        }

        return fileslist
    }


}