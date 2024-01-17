package com.yash.messiahwamr.models

import androidx.room.Entity
import androidx.room.PrimaryKey
//@PrimaryKey(autoGenerate=true) var id:Long
@Entity(tableName = "messengers")
data class chatHomeModel(@PrimaryKey(autoGenerate = true) var id:Int,
                         var tag:String,
                         var profilePic:Int,
                         var name:String,
                         var lastMsg:String,
                         var lMsgTime:Long,
                         var isGroup:Boolean,
                         var group:String
                         )