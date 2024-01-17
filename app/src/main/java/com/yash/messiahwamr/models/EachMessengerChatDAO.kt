package com.yash.messiahwamr.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface EachMessengerChatDAO{

    @Query("SELECT * from messengers WHERE tag=:tag")
    fun getCompleteChat(tag:String):LiveData<List<chatHomeModel>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addNewMessage(msg:chatHomeModel):Long
}


//3sNyl9aMgwj6/EPEf+II+bGFOg6cHX268AZRQNwPgzg=
//3sNyl9aMgwj6/EPEf+II+bGFOg6cHX268AZRQNwPgzg=