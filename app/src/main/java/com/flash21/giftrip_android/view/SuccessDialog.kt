package com.flash21.giftrip_android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.DialogSuccessBinding

class SuccessDialog : Fragment() {

    private lateinit var dataBinding: DialogSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_success, container, false)
        return dataBinding.root
    }
}