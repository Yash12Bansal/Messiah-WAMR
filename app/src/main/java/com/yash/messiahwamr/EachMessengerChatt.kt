package com.yash.messiahwamr

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.messiahwamr.adapters.EachMessenChatAdapter
import com.yash.messiahwamr.models.chatHomeModel
import com.yash.messiahwamr.viewmodels.EachMessengerChatViewModel
import com.yash.messiahwamr.viewmodels.EachMessengerChatViewModelFactory
import com.yash.messiahwamr.R
import kotlinx.android.synthetic.main.fragment_each_messenger_chat.*


class EachMessengerChatt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_each_messenger_chatt)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        actionBar?.setHomeButtonEnabled(true)
//        actionBar?.setDisplayHomeAsUpEnabled(true)


        var sentTag=intent.getStringExtra("tag")
        var tt=intent.getStringExtra("name")
        var viewModelFactory= EachMessengerChatViewModelFactory(applicationContext as Application,sentTag.toString())

        var viewModel= ViewModelProvider(this,viewModelFactory).get(EachMessengerChatViewModel::class.java)


        var linearLayoutManager=LinearLayoutManager(this@EachMessengerChatt)
        linearLayoutManager.stackFromEnd=true
        recyclerView.apply{
            layoutManager= linearLayoutManager
        }
        var adapter= EachMessenChatAdapter(this)
        recyclerView.adapter=adapter
//        adapter.registerAdapterDataObserver(object : AdapterDataObserver() {
//            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
//                recyclerView.smoothScrollToPosition(adapter.getItemCount())
//            }
//        })
//        mMessages, null,
        supportActionBar?.title=tt
        viewModel.eachMessengerChatList.observe(this,{data:List<chatHomeModel>->
            adapter.setData(data)
        })

    }
}