package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.SettingFragmentViewModel

class SettingFragmentViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SettingFragmentViewModel::class.java)){
            return SettingFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}