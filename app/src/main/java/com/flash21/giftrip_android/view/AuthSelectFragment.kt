package com.flash21.giftrip_android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentAuthSelectBinding
import com.flash21.giftrip_android.viewmodel.AuthSelectFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.AuthSelectFragmentViewModelFactory


class AuthSelectFragment : Fragment() {
    private lateinit var dataBinding : FragmentAuthSelectBinding
    private lateinit var viewModel : AuthSelectFragmentViewModel
    private lateinit var viewModelFactory : AuthSelectFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth_select, container, false)
        viewModelFactory = AuthSelectFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthSelectFragmentViewModel::class.java)

        dataBinding.btnLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_auth_select_to_navigation_login)
        }
        dataBinding.btnRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_auth_select_to_navigation_get_phoneNumber)
        }
        return dataBinding.root
    }

}