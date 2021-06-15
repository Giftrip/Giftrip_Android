package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.AuthActivityViewModel

class AuthActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthActivityViewModel::class.java)){
            return AuthActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}