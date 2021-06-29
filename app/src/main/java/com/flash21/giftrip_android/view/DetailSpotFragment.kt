package com.flash21.giftrip_android.view

import android.app.PendingIntent
import android.content.Context
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentSpotDetailBinding
import com.flash21.giftrip_android.viewmodel.DetailSpotFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.DetailSpotFragmentViewModelFactory

class DetailSpotFragment : Fragment() {

    private lateinit var dataBinding: FragmentSpotDetailBinding
    private lateinit var viewModel: DetailSpotFragmentViewModel
    private lateinit var viewModelFactory: DetailSpotFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_spot_detail, container, false)
        viewModelFactory = DetailSpotFragmentViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailSpotFragmentViewModel::class.java)

        val nfcDialog = NfcDialogFragment()


        with(dataBinding){
            nfcButton.setOnClickListener {
                nfcDialog.show(parentFragmentManager, "nfcDialog")
            }
        }

        return dataBinding.root
    }
}