package com.flash21.giftrip_android.view

import android.app.PendingIntent
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.ActivityMainBinding
import com.flash21.giftrip_android.viewmodel.MainActivityViewModel
import com.flash21.giftrip_android.viewmodel_factory.MainActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

/*
*
* 담당자 : 한승재, 양준혁, 이용재
* 생성일자 : 2021.05.13.
* 최근 수정일: 2021.05.13.
*
* */

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    private lateinit var fragment: Fragment

    private lateinit var adapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewModel
        dataBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        //Bottom Navigation
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }
}