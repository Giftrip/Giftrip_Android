package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.LoginFragmentViewModel

class LoginFragmentViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginFragmentViewModel::class.java)){
            return LoginFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}