package com.yash.messiahwamr

import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.provider.MediaStore

class GalleryObserver(handler: Handler,var context:Context) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        println("HDODOOEOOOGAYO FOEROEROO FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFOEIWOIOIEWROEOIRIOORIOI")
        // get the updated data from the MediaStore API
        val cursor = context?.contentResolver?.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )
        // do something with the updated data
    }
}

//import android.os.Environment
//import android.os.FileObserver
//import android.os.FileUtils
//import java.io.File
//
//class MediaObserving:FileObserver(File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images").toString(),
//    ALL_EVENTS) {
//    var List =emptyList<File>()
//    override fun onEvent(p0: Int, p1: String?) {
//
////        println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
//
////        var srcF=File(p1)
////        var temp:File?=null
//        if(p0== MOVED_TO){
//            var file=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}$p1")
////
////            var srcF=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}${p1}")
//            println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
////            println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+srcF.absoluteFile)
////            var file=File.createTempFile("hel",".jpg")
////
////
////            temp=org.apache.commons.io.FileUtils.
//            var destF=File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah${File.separator}All Images")
//            if(!destF.exists()){
//                var s=destF.mkdirs()
//                println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
//            }
//
//            org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
//
//        }
//        if(p0 == DELETE){
//            println("sldkdobsererer")
//            try{
//                var destF=File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah${File.separator}Deleted Images${File.separator}")
////                var ff=File(destF,p1)
//                var file=File(Environment.getExternalStorageDirectory(),"WhatsApp Messiah${File.separator}All Images${File.separator}$p1")
//                org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
//
//
//
////                copyFil
////                temp?.copyTo(destF,false, DEFAULT_BUFFER_SIZE)
//
//
//            }
//            catch(e:Exception){
//                println(e)
//            }
//        }
//
//    }
//
//
//}