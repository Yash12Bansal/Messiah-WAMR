package com.yash.messiahwamr.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
//import com.yash.messiah.models.EachMessengerChatModel
import com.yash.messiahwamr.models.chatHomeModel
import com.yash.messiahwamr.repositories.EachMessengerChatRepo

class EachMessengerChatViewModel(context:Application,tag:String):ViewModel() {

    var repo=EachMessengerChatRepo(context)
//    var repo2=chatHomeRepository(context)
    var eachMessengerChatList:LiveData<List<chatHomeModel>>

    init{
            eachMessengerChatList=repo.getCompleteChat(tag)
    }

//    suspend fun addNewMess(msg:chatHomeModel){
//        repo2.addMessenger(msg)
//    }
//
}