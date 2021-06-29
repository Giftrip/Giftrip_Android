package com.flash21.giftrip_android.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.DialogYesNoBinding

class YesOrNoDialogFragment : DialogFragment() {

    private lateinit var dataBinding: DialogYesNoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_yes_no, container, false)

        dataBinding.apply {
            yesButton.setOnClickListener {
            }

            noButton.setOnClickListener {
                dialog!!.dismiss()
            }
        }

        return dataBinding.root
    }
}