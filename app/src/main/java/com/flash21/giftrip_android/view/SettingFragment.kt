package com.flash21.giftrip_android.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentSettingBinding
import com.flash21.giftrip_android.model.sharedPreference.MyApplication
import com.flash21.giftrip_android.viewmodel.SettingFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.SettingFragmentViewModelFactory

/*
*
* 담당자 : 양준혁
* 생성일자 : 2021.05.13.
* 최근 수정일: 2021.05.13.
*
* */

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

        dataBinding.btnLogout.setOnClickListener(){
            MyApplication.prefs.setString("AccessToken","null")
            startActivity(Intent(activity,AuthActivity::class.java))
        }
        return dataBinding.root
    }
}