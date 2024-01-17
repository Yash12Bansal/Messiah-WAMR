package com.yash.messiahwamr.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.yash.messiahwamr.models.chatHomeModel
//import com.yash.messiah.models.EachMessengerChatModel
import com.yash.messiahwamr.models.chatsDatabase

class EachMessengerChatRepo(context: Application) {

    var EachMessengerChatDao=chatsDatabase.getDatabase(context).EachMessengerChatDao()

    fun getCompleteChat(tag:String):LiveData<List<chatHomeModel>>{
        return EachMessengerChatDao.getCompleteChat(tag)
    }

//    suspend fun addNewMessage(msg:chatHomeModel){
//        EachMessengerChatDao.addNewMessage(msg)
//    }

}