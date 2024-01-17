package com.yash.messiahwamr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.messiahwamr.adapters.chatHomeAdapter
import com.yash.messiahwamr.models.chatHomeModel
import com.yash.messiahwamr.viewmodels.chatHomeViewModel
import com.yash.messiahwamr.R
import kotlinx.android.synthetic.main.fragment_chat_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [chatHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class chatHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//
//    var nR:BroadcastReceiver?=null
    lateinit var viewModel:chatHomeViewModel
//    var list = arrayListOf<chatHomeModel>(chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939"),
//            chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939"),
//    chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939"),
//    chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939"),
//    chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939"),
//    chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939"),
//    chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939"),
//    chatHomeModel(4,"Sample User","Hi aman wher are you and what the hell are you doing!!","10/2/1939")
//)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_home, container, false)

    }

    override fun onResume() {
        super.onResume()

//        nR=NotificationReceive.getinstance()
//        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(nR!!, IntentFilter(MSG_NOTIFICATION))

    }
    override fun onDestroy() {
        super.onDestroy()
//        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(nR!!)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(chatHomeViewModel::class.java)
//        viewModel._chatHomeList.value=ArrayList()

//        viewModel.getMsgs()

        recyclerView.apply {
            layoutManager=LinearLayoutManager(requireActivity())
        }
        var adapter=chatHomeAdapter()
        recyclerView.adapter=adapter
        viewModel._chatHomeList.observe(viewLifecycleOwner, Observer {data:List<chatHomeModel>->
            adapter.setData(data)
            if(viewModel._chatHomeList.value?.size==0 || viewModel._chatHomeList.value==null){

                Thread{
                    view.postDelayed(object:Runnable{
                        override fun run() {
                            recyclerView.visibility=View.INVISIBLE
                            no_msg.visibility=View.VISIBLE

                        }
                    },500)
                }
            }
            else{
                recyclerView.visibility=View.VISIBLE
                no_msg.visibility=View.INVISIBLE
                println("eeeeeeeeeeeeeeeeeeeeeeeeeeeelllllllllllllllllllllllllllllllllllssssssseeeeeeeeeee")

            }

        })

//
    }
//    inner class NotificationReceive: BroadcastReceiver() {
//        var instance:NotificationReceive?=null
//
//        fun getinstance():NotificationReceive?{
//            if(instance==null){
//                return NotificationReceive()
//            }
//            else{
//                return instance
//            }
//        }
//        override fun onReceive(p0: Context?, p1: Intent?) {
//            if(p1?.action==MSG_NOTIFICATION){
//                var name=p1?.getStringExtra("name")
//                var msg=p1?.getStringExtra("msg")
//                var tag=p1?.getStringExtra("tag")
//                var time=(p1?.getStringExtra("time"))?.toLong()
//
//                println(msg)
//                CoroutineScope(IO).launch {
//                    viewModel.addNewMsg(chatHomeModel(0,tag!!,1,name!!,msg!!,time!!))
//                }
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
//            }
//
//        }
//    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment chatHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            chatHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}