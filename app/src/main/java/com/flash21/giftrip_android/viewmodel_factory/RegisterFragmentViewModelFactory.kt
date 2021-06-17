package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.RegisterFragmentViewModel

class RegisterFragmentViewModelFactory  : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(RegisterFragmentViewModel::class.java)){
            return RegisterFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}