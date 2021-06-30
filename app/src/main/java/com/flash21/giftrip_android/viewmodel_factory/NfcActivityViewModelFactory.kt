package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.MainActivityViewModel
import com.flash21.giftrip_android.viewmodel.NfcActivityViewModel

class NfcActivityViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NfcActivityViewModel::class.java)){
            return NfcActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}