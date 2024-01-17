package com.yash.messiahwamr.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.yash.messiahwamr.models.chatHomeDAO
import com.yash.messiahwamr.models.chatHomeModel
import com.yash.messiahwamr.models.chatsDatabase

class chatHomeRepository(context: Application) {

    var chatHomeDao:chatHomeDAO=chatsDatabase.getDatabase(context).chatHomeDao()

    fun getMessengers():LiveData<List<chatHomeModel>>{
        var ll=chatHomeDao.getAllMessengers()
//        var l=ll.value
//        if(l==null){
//
//        }
//        if(ll==null){
//            ll=LiveData<ArrayList<chatHomeModel>>()
//        }
        return ll /* = java.util.ArrayList<com.yash.messiah.models.chatHomeModel> */
    }

     suspend fun addMessenger(messenger:chatHomeModel){
        chatHomeDao.addMessenger(messenger)

    }
//    suspend fun update(msg:chatHomeModel){
//        chatHomeDao.updateMessenger(msg)
//    }


}