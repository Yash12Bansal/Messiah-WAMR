package com.yash.messiahwamr.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface chatHomeDAO{

    @Query("SELECT * , max(lMsgTime) from messengers GROUP BY tag ORDER BY lMsgTime DESC")
    fun getAllMessengers():LiveData<List<chatHomeModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMessenger(msg:chatHomeModel):Long

}