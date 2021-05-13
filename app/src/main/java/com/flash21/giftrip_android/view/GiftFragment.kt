package com.flash21.giftrip_android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentGiftBinding
import com.flash21.giftrip_android.viewmodel.GiftFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.GiftFragmentViewModelFactory

/*
*
* 담당자 : 이용재
* 생성일자 : 2021.05.13.
* 최근 수정일: 2021.05.13.
*
* */

class GiftFragment : Fragment(){

    private lateinit var dataBinding : FragmentGiftBinding
    private lateinit var viewModel : GiftFragmentViewModel
    private lateinit var viewModelFactory : GiftFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gift, container, false)
        viewModelFactory = GiftFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(GiftFragmentViewModel::class.java)

        return dataBinding.root
    }
}