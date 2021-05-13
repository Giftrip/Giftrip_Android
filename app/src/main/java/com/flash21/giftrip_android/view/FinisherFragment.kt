package com.flash21.giftrip_android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentFinisherBinding
import com.flash21.giftrip_android.viewmodel.FinisherFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.FinisherFragmentViewModelFactory

/*
*
* 담당자 : 이용재
* 생성일자 : 2021.05.13.
* 최근 수정일: 2021.05.13.
*
* */

class FinisherFragment : Fragment(){

    private lateinit var dataBinding : FragmentFinisherBinding
    private lateinit var viewModel : FinisherFragmentViewModel
    private lateinit var viewModelFactory : FinisherFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_finisher, container, false)
        viewModelFactory = FinisherFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(FinisherFragmentViewModel::class.java)

        return dataBinding.root
    }
}