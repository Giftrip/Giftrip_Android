package com.flash21.giftrip_android.view

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
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

    private var adapter: NfcAdapter? = null

    private var tagData = ""

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