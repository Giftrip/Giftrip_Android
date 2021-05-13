package com.flash21.giftrip_android.view

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentHomeBinding
import com.flash21.giftrip_android.viewmodel.HomeFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.HomeFragmentViewModelFactory

/*

* 담당자 : 한승재
* 생성일자 : 2021.05.13.
* 최근 수정일: 2021.05.13.
*
* */

class HomeFragment : Fragment(){

    private lateinit var dataBinding : FragmentHomeBinding //HomeFragment DataBinding 객체
    private lateinit var viewModel : HomeFragmentViewModel //HomeFragment viewModel 객체
    private lateinit var viewModelFactory : HomeFragmentViewModelFactory //HomeFragment viewModel Factory 객체

    //onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModelFactory = HomeFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)

        dataBinding.apply {

        }

        return dataBinding.root
    }
}