package com.yash.messiahwamr.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EachMessengerChatViewModelFactory(var context:Application,var tag:String):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(EachMessengerChatViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return EachMessengerChatViewModel(context,tag) as T
        }
        throw IllegalArgumentException("wringngng")
    }
}