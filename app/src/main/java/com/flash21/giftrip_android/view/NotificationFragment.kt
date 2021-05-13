package com.flash21.giftrip_android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentNotificationBinding
import com.flash21.giftrip_android.viewmodel.NotificationFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.NotificationFragmentViewModelFactory

class NotificationFragment : Fragment(){

    private lateinit var dataBinding : FragmentNotificationBinding
    private lateinit var viewModel : NotificationFragmentViewModel
    private lateinit var viewModelFactory : NotificationFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        viewModelFactory = NotificationFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(NotificationFragmentViewModel::class.java)

        return dataBinding.root
    }
}