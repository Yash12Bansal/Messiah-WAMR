package com.yash.messiahwamr.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yash.messiahwamr.R
//import com.statussaverwhatsapp.messiah.models.chatHomeModel
//import com.statussaverwhatsapp.messiah.R
//import com.yash.messiah.models.EachMessengerChatModel
import com.yash.messiahwamr.models.chatHomeModel
//import com.yash.messiah.R
import java.util.*

class EachMessenChatAdapter(var context: Context):RecyclerView.Adapter<EachMessenChatAdapter.CustomViewHolder>() {

    var eachChatList= emptyList<chatHomeModel>()

    inner class CustomViewHolder(v: View): RecyclerView.ViewHolder(v){
        var tv=v.findViewById<TextView>(R.id.mess)
        var sender=v.findViewById<TextView>(R.id.sender)
        var time=v.findViewById<TextView>(R.id.time)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var layout=LayoutInflater.from(parent.context).inflate(R.layout.each_message_item,parent,false)
        return CustomViewHolder(layout)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        with(holder){
            tv.setText(eachChatList[position].lastMsg)
            if(eachChatList[position].isGroup){
                var arr = arrayOf(
                    ContextCompat.getColor(context, R.color.blue),
                    ContextCompat.getColor(context,R.color.red),
                    ContextCompat.getColor(context,R.color.yellow),
                    ContextCompat.getColor(context,R.color.orange),
                    ContextCompat.getColor(context,R.color.green),
                    ContextCompat.getColor(context,R.color.pink),
                    ContextCompat.getColor(context,R.color.gy),
                    ContextCompat.getColor(context,R.color.lb)
                )
                var random=(0..arr.size-1).random()
                sender.setTextColor(arr[random])


                var delimit=eachChatList[position].group
                var m=eachChatList[position].name.split(delimit)
                if(m[1].toString()[0]==':'){
                    var ss=m[1].substring(1).trimEnd().trimStart()
                    sender.setText(ss)
//                    (context as EachMessengerChatt).supportActionBar?.title=ss

                }
                else{
                    sender.setText(m[1].trimEnd().trimStart())
//                    (context as EachMessengerChatt).supportActionBar?.title=m[1].trimEnd().trimStart()

                }
            }
            else{
                sender.text=""
                sender.textSize= 0F
//                (context as EachMessengerChatt).supportActionBar?.title=eachChatList[position].name

            }
            var cd= Date(eachChatList?.get(position)?.lMsgTime!!).toString()
            var list=cd.split(" ")
            var timee=list[3]
            var tn=timee.split(":")
            var tf=tn[0]+":"+tn[1]

            var datee=tf+" "+list[2]+" "+list[1]+" "+list[5]
//            var date= Date(eachChatList[position].lMsgTime)
            time.setText(datee)
        }
    }

    override fun getItemCount(): Int {
        return eachChatList.size
    }


    fun setData(list:List<chatHomeModel>){
        var p=this.eachChatList.size
        this.eachChatList=list
        notifyItemInserted(p)
    }

}