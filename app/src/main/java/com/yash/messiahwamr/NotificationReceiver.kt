//package com.yash.messiah
//
//import android.app.Application
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import androidx.lifecycle.ViewModelProviders
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.yash.messiah.models.chatHomeDAO
//import com.yash.messiah.models.chatHomeModel
//import com.yash.messiah.models.chatsDatabase
//import com.yash.messiah.viewmodels.chatHomeViewModel
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class NotificationReceive: BroadcastReceiver() {
//    var chatHomeDao: chatHomeDAO = chatsDatabase.getDatabase(Application()).chatHomeDao()
//    companion object{
//        var instance:BroadcastReceiver?=null
//
//        fun getinstance():BroadcastReceiver?{
//            if(instance==null){
//                instance=NotificationReceive()
//
//            }
//            return instance
//        }
//
//    }
//
//    override fun onReceive(p0: Context?, p1: Intent?) {
//
//        if(p1?.action==MSG_NOTIFICATION){
//            var name=p1?.getStringExtra("name")
//            var msg=p1?.getStringExtra("msg")
//            var tag=p1?.getStringExtra("tag")
//            var time=(p1?.getStringExtra("time"))?.toLong()
//
//            println(msg)
//            CoroutineScope(Dispatchers.IO).launch {
//                chatHomeDao.addMessenger(chatHomeModel(0,tag!!,1,name!!,msg!!,time!!))
//            }
//
////                if(viewModel._chatHomeList.value?.find { it.tag==tag }!=null){
////                    CoroutineScope(IO).launch {
////                        viewModel.updateMessenger(chatHomeModel(0,tag!!,1,name!!,msg!!,time!!))
////
//////                        EachMessengerChat().viewModel.addNewMess(EachMessengerChatModel(1,tag!!,msg!!) )
////
////                    }
////                }
////                else{
////                    CoroutineScope(IO).launch {
////                        viewModel.addNewMsg(chatHomeModel(0,tag!!,1,name!!,msg!!,time!!))
//////                        EachMessengerChat().viewModel.addNewMess(EachMessengerChatModel(1,tag!!,msg!!) )
////                    }
////                }
//
//
//            //            var chatHomeViewModel=ViewModelProviders.of(chatHome()).get(chatHomeViewModel::class.java)
////                println("sdfdfdffdd")
////                println(name)
////                println(msg)
//
//        }
//
//    }
//}
