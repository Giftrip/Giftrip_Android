package com.flash21.giftrip_android.view

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.ActivityMainBinding
import com.flash21.giftrip_android.viewmodel.MainActivityViewModel
import com.flash21.giftrip_android.viewmodel_factory.MainActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.nio.charset.Charset
import kotlin.experimental.and
import kotlin.properties.Delegates

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

    //nfc get tag variable
    private val ErrorDetected = "No NFC Tag Detected"
    private val WriteSuccess = "Text Written Successfully!"
    private val WriteError = "Error during Writing, Try Again!"

    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent
    private lateinit var writingTagFilter: IntentFilter
    private lateinit var myTag: Tag
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this

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

        //Nfc


    }

}