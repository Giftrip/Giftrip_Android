package com.flash21.giftrip_android.view

import android.content.Intent
import android.net.Uri
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
    private var tag: Tag?  = null //Get Tag
    private lateinit var ndefTag: Ndef //Ndef Tag parsing
    private var ndefSize by Delegates.notNull<Int>() //Tag Size
    private var writable by Delegates.notNull<Boolean>() //Writable
    private lateinit var type: String //Tag Type
    private lateinit var id: String //Tag Id
    private lateinit var payloadStr: String

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

        //Nfc
        val nfcAdapter = NfcAdapter.getDefaultAdapter(applicationContext)

        if (nfcAdapter == null) {
            // NFC 미지원단말
            Toast.makeText(applicationContext, "NFC를 지원하지 않는 단말기입니다.", Toast.LENGTH_SHORT).show()
            return
        }else{
            Toast.makeText(applicationContext, "NFC를 지원하는 단말기입니다.", Toast.LENGTH_SHORT).show()
        }

        tag = intent.getParcelableExtra(NfcAdapter.ACTION_TECH_DISCOVERED)
        if(tag != null){
            ndefTag = Ndef.get(tag)
            ndefSize = ndefTag.maxSize
            writable = ndefTag.isWritable
            type = ndefTag.type
            id = byteArrayToHexString(tag?.id)

            Toast.makeText(applicationContext, "data: $tag", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onResume() {
        super.onResume()
        val message = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES) ?: return

        for(i in 0..message.size){
            setReadTagData(message[0] as NdefMessage?)
        }

        with(viewModel) {
            getPayloadStr(payloadStr)
            getQuiz()
            Toast.makeText(applicationContext, "data: $youtube", Toast.LENGTH_LONG).show()
        }

    }

    //Change TagID
    private fun byteArrayToHexString(b: ByteArray?): String{
        val length: Int? = b?.size
        var data = String()

        if (length != null) {
            for(i in 0 until length){
                data += Integer.toHexString((b[i].toInt() shr 4) and 0xf)
                data += Integer.toHexString((b[i] and 0xf).toInt())
            }
        }

        return data
    }

    private fun setReadTagData(ndefMessage: NdefMessage?) {

        if (ndefMessage == null) {
            return
        }

        var message = ""
        message += """
             $ndefMessage
             """.trimIndent()

        val records = ndefMessage.records

        for (rec in records) {

            val payload = rec.payload
            var textEncoding = "UTF-8"

            if (payload.isNotEmpty()) textEncoding = if (payload[0].toInt() and 128 == 0) "UTF-8" else "UTF-16"

            val tnf = rec.tnf
            val type = String(rec.type)
            payloadStr = String(rec.payload, Charset.forName(textEncoding))

            Toast.makeText(applicationContext, "payloadStr: $payloadStr", Toast.LENGTH_SHORT).show()
        }
    }

}