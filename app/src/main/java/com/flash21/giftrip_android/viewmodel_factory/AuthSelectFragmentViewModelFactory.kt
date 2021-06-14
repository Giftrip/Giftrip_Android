package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.AuthSelectFragmentViewModel

class AuthSelectFragmentViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthSelectFragmentViewModel::class.java)){
            return AuthSelectFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}