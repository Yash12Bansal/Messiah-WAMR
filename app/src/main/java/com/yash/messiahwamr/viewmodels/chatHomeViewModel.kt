package com.yash.messiahwamr.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.yash.messiahwamr.models.chatHomeModel
import com.yash.messiahwamr.repositories.chatHomeRepository

class chatHomeViewModel(application:Application):AndroidViewModel(application) {

    var repo=chatHomeRepository(application)
    var _chatHomeList:LiveData<List<chatHomeModel>>

    init{
//        var chatHomeDao=chatsDatabase.getDatabase(application).chatHomeDao()
        _chatHomeList=repo.getMessengers()
    }
//    val chatHomeList:LiveData<List<chatHomeModel>>
//    get()=_chatHomeList



//    fun getMsgs(){
//        var list=repo.getMessengers()
//        _chatHomeList.postValue(list.value as ArrayList<chatHomeModel>?)
////        var l:ArrayList<chatHomeModel> =ArrayList()
////        if(list.value!=null){
////            l= list.value.
////            _chatHomeList.postValue(ArrayList())
////
////        }
////        else{
////            _chatHomeList.postValue(l)
////
////        }
//
//
//    }
//    suspend fun updateMessenger(msg:chatHomeModel){
//        repo.update(msg)
//    }

    suspend fun addNewMsg(msg:chatHomeModel){
        repo.addMessenger(msg)


//        var l=_chatHomeList.value
//
//        var x=_chatHomeList.value?.find { it.tag==msg.tag }
//        if(x==null){
//            l?.add(msg)
//            _chatHomeList.postValue(l!!)
//        }
//        else{
//
//            l?.get(l.indexOf(x))?.lastMsg=msg.lastMsg
//            _chatHomeList.postValue(l!!)
//
//        }
//

    }
}