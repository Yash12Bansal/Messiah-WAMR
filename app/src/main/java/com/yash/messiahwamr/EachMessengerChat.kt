package com.yash.messiahwamr

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.messiahwamr.adapters.EachMessenChatAdapter
import com.yash.messiahwamr.models.chatHomeModel
import com.yash.messiahwamr.viewmodels.EachMessengerChatViewModel
import com.yash.messiahwamr.viewmodels.EachMessengerChatViewModelFactory
//import com.yash.messiah.R
import kotlinx.android.synthetic.main.fragment_each_messenger_chat.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EachMessengerChat.newInstance] factory method to
 * create an instance of this fragment.
 */
class EachMessengerChat : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_each_messenger_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sentTag=arguments?.getString("tag")
        var viewModelFactory= EachMessengerChatViewModelFactory(context?.applicationContext as Application,sentTag.toString())

        var viewModel=ViewModelProvider(this,viewModelFactory).get(EachMessengerChatViewModel::class.java)


        recyclerView.apply{
            layoutManager=LinearLayoutManager(requireActivity())
        }
        var adapter=EachMessenChatAdapter(view.context)
        recyclerView.adapter=adapter


        viewModel.eachMessengerChatList.observe(viewLifecycleOwner,{data:List<chatHomeModel>->
            adapter.setData(data)
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EachMessengerChat.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EachMessengerChat().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}