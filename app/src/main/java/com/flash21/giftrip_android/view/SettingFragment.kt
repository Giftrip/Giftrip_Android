package com.flash21.giftrip_android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentSettingBinding
import com.flash21.giftrip_android.viewmodel.SettingFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.SettingFragmentViewModelFactory

class SettingFragment : Fragment(){

    private lateinit var dataBinding : FragmentSettingBinding
    private lateinit var viewModel : SettingFragmentViewModel
    private lateinit var viewModelFactory : SettingFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        viewModelFactory = SettingFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingFragmentViewModel::class.java)

        return dataBinding.root
    }
}