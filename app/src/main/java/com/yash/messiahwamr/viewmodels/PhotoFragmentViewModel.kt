package com.yash.messiahwamr.viewmodels

import android.app.Application
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yash.messiahwamr.models.ModelClass
import java.io.File

class PhotoFragmentViewModel(application: Application): AndroidViewModel(application) {

    var _l=MutableLiveData<List<File>>()
    val l: LiveData<List<File>>
        get() =_l

    var _l2=MutableLiveData<List<File>>()
    val l2: LiveData<List<File>>
        get() =_l2

    init{
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            var file=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}")
            var file2=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")

            if(file2.exists() ){
                if(file2.listFiles()!=null){
                    _l2.value=file2.listFiles().toList()
                }
                else{
                    _l2.value= emptyList()
                }
            }
            else{
                _l2.value= emptyList()
            }
//            var file=File(application.getFilesDir(),"My Media")
            if(file.exists()){

                var files=file.listFiles().toList()
                if(files==null){
                    _l.value= emptyList()
                }
                else{
                    var ll=files
                    _l.value=ll

                }

            }
            else{
                _l.value= emptyList()
            }
//        }
//        else{
//            var listPhotos=File(application?.getExternalFilesDir(null)?.absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")
//            if(listPhotos.listFiles()==null){
//                _l.value= emptyList()
//            }
//            else{
//
//                var ll=listPhotos.listFiles().toList()
//                _l.value=ll
//
//            }
//
//        }
    }

    fun reinitalize(application: Application){
//        println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFCALLELDLDLDLLFD")
//        var list=_l.value
//        list?.toMutableList()?.add(file)
//        _l.postValue(list?.toList())
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
        var file=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}")
        var file2=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")

        println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")
        if(file2.exists() ){
            if(file2.listFiles()!=null){
                _l2.value=file2.listFiles().toList()
            }
            else{
                _l2.value= emptyList()
            }
        }
        else{
            _l2.value= emptyList()
        }
//            var file=File(application.getFilesDir(),"My Media")
        if(file.exists()){

            var files=file.listFiles().toList()
            if(files==null){
                _l.value= emptyList()
            }
            else{
                var ll=files
                _l.value=ll

            }

        }
        else{
            _l.value= emptyList()
        }
//        }
//
//        else{
//            var listPhotos= File(getApplication<Application>()?.getExternalFilesDir(null)?.absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")
//            println(":::::::::::::::::::::::::::::FDkfjdlfjeiofffffKDJFEIOEORPPPPPPPPPPPPDOOOOOOOOOOOOOOOOOOOOOOOOO    "+listPhotos.exists())
//            if(listPhotos.listFiles()==null){
//                _l.value= emptyList()
//            }
//            else{
//
//                var ll=listPhotos.listFiles().toList()
//                _l.value=ll
//
//            }
//
//        }

    }



//    fun addPhotos(){
//
//    }
}