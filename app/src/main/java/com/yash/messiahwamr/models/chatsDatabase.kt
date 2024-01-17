package com.yash.messiahwamr.models

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//,EachMessengerChatModel::class
@Database(entities = [chatHomeModel::class ] , version = 5)

abstract class chatsDatabase:RoomDatabase() {
    abstract fun chatHomeDao():chatHomeDAO

    abstract fun EachMessengerChatDao():EachMessengerChatDAO
    companion object{

        @Volatile
        private var instance:chatsDatabase?=null

        fun getDatabase(context:Application)= instance?:
        synchronized(this){
            Room.databaseBuilder(
                context.applicationContext,
                chatsDatabase::class.java,
                "chats_database"
            ).fallbackToDestructiveMigration().build().also{ instance=it}

        }
    }

}