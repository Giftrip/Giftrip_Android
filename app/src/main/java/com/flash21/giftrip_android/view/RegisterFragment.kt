package com.flash21.giftrip_android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentRegisterBinding
import com.flash21.giftrip_android.viewmodel.RegisterFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.RegisterFragmentViewModelFactory

class RegisterFragment : Fragment() {
    private lateinit var dataBinding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterFragmentViewModel
    private lateinit var viewModelFactory: RegisterFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_phonenumber, container, false)
        viewModelFactory = RegisterFragmentViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RegisterFragmentViewModel::class.java)

        return dataBinding.root
    }
}