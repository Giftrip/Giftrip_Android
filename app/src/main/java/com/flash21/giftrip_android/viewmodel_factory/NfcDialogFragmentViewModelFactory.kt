package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.MainActivityViewModel
import com.flash21.giftrip_android.viewmodel.NfcDialogFragmentViewModel

class NfcDialogFragmentViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NfcDialogFragmentViewModel::class.java)){
            return NfcDialogFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}