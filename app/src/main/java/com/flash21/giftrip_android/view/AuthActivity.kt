package com.flash21.giftrip_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.ActivityAuthBinding
import com.flash21.giftrip_android.viewmodel.AuthActivityViewModel
import com.flash21.giftrip_android.viewmodel_factory.AuthActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class AuthActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityAuthBinding
    private lateinit var viewModel : AuthActivityViewModel
    private lateinit var viewModelFactory : AuthActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this@AuthActivity, R.layout.activity_auth)
        viewModelFactory = AuthActivityViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthActivityViewModel::class.java)

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navView.setupWithNavController(navController)
    }
}