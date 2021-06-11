package com.flash21.giftrip_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentLoginBinding
import com.flash21.giftrip_android.viewmodel.LoginFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.LoginFragmentViewModelFactory

/*
* 담당자 : 이용재
* 생성일자 : 2021.06.11.
* 최근 수정일: 2021.06.11.
*
* */


class LoginFragment : Fragment() {

    private lateinit var dataBinding : FragmentLoginBinding
    private lateinit var viewModel : LoginFragmentViewModel
    private lateinit var viewModelFactory : LoginFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gift, container, false)
        viewModelFactory = LoginFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginFragmentViewModel::class.java)

        return dataBinding.root
    }
}