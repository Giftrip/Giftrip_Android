package com.flash21.giftrip_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.ActivityAuthBinding
import com.flash21.giftrip_android.viewmodel.AuthActivityViewModel
import com.flash21.giftrip_android.viewmodel_factory.AuthActivityViewModelFactory

class AuthActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityAuthBinding
    private lateinit var viewModel : AuthActivityViewModel
    private lateinit var viewModelFactory : AuthActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this@AuthActivity, R.layout.activity_auth)
        viewModelFactory = AuthActivityViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthActivityViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.auth_fragment) as NavHostFragment
    }
}