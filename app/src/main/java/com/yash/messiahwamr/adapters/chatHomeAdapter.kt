package com.yash.messiahwamr.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.yash.messiahwamr.EachMessengerChatt
import com.yash.messiahwamr.R
import com.yash.messiahwamr.models.chatHomeModel
import kotlinx.android.synthetic.main.chat_list_item.view.*
import kotlinx.android.synthetic.main.photo_item.view.*
import java.util.*

class chatHomeAdapter: RecyclerView.Adapter<chatHomeAdapter.CustomViewHolder>() {

    var chatHomeList= emptyList<chatHomeModel>()
    var titleName= emptyList<String>()
    inner class CustomViewHolder(var v: View): RecyclerView.ViewHolder(v){
        var profileP=v.findViewById<ImageView>(R.id.profileP)
        var name=v.findViewById<TextView>(R.id.name)
        var msg=v.findViewById<TextView>(R.id.msg)
        var date=v.findViewById<TextView>(R.id.date)
        init{
            itemView.setOnClickListener{
                var intent= Intent(it.context,EachMessengerChatt::class.java)
                var bundle= Bundle()
                intent.putExtra("tag",chatHomeList[adapterPosition].tag)
                intent.putExtra("name",itemView.name.text)
                startActivity(it.context,intent,null)

//                it.findNavController().navigate(R.id.action_chatHome_to_eachMessengerChat,bundle)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var layout=LayoutInflater.from(parent.context).inflate( R.layout.chat_list_item,parent,false)
        return CustomViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.profileP.setImageResource(R.drawable.user__1_)
        with(holder){

//            var nn=chatHomeList?.get(position)?.name
//            var l=nn?.split(":")[1]
            if(chatHomeList?.get(position)?.isGroup!!){
                profileP.setImageResource(R.drawable.group__2_)
                if(chatHomeList?.get(position)?.group?.contains("messages)")==true){
                    var ll=chatHomeList?.get(position)?.group?.split("messages)")
                    var l0= ll?.get(0)?.trimEnd()?.trimStart()
                    var f=l0?.split(" ")
                    var ss=""
                    for(i in 0..((f?.size)?.minus(2) ?: -1)){
                        ss=ss+f?.get(i)+" "
                    }
                    name.setText(ss)
                }
                else{
                    name.setText(chatHomeList?.get(position)?.group)
                }
            }
            else{
                profileP.setImageResource(R.drawable.user__2_)
                name.setText(chatHomeList?.get(position)?.name)
            }

            msg.setText(chatHomeList?.get(position)?.lastMsg)
            var cd= Date(chatHomeList?.get(position)?.lMsgTime!!).toString()
            var list=cd.split(" ")
            var datee=list[2]+" "+list[1]+" "+list[5]
            date.setText(datee)

        }
//        titleName[position]=holder.name.text.toString()

    }

    override fun getItemCount(): Int {
        return chatHomeList.size
    }

    fun setData(messengers:List<chatHomeModel>){
        this.chatHomeList=messengers
        notifyDataSetChanged()

    }
}