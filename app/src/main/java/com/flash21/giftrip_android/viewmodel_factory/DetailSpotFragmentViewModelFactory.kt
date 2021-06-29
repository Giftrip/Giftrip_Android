package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.AuthSelectFragmentViewModel
import com.flash21.giftrip_android.viewmodel.DetailSpotFragmentViewModel

class DetailSpotFragmentViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailSpotFragmentViewModel::class.java)){
            return DetailSpotFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}