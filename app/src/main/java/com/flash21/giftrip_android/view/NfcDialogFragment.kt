package com.flash21.giftrip_android.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.DialogNfcBinding
import com.flash21.giftrip_android.viewmodel.NfcDialogFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.NfcDialogFragmentViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NfcDialogFragment : BottomSheetDialogFragment() {

    private lateinit var dataBinding : DialogNfcBinding
    private lateinit var viewModel: NfcDialogFragmentViewModel
    private lateinit var viewModelFactory: NfcDialogFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_nfc, container, false)
        viewModelFactory = NfcDialogFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(NfcDialogFragmentViewModel::class.java)

        dataBinding.apply {
            
        }

        return dataBinding.root

    }
}