package com.flash21.giftrip_android.view

import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.ActivitySpotBinding
import com.flash21.giftrip_android.viewmodel.NfcActivityViewModel
import com.flash21.giftrip_android.viewmodel_factory.NfcActivityViewModelFactory
import java.nio.charset.Charset
import kotlin.experimental.and
import kotlin.properties.Delegates

class SpotActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivitySpotBinding
    private lateinit var viewModel: NfcActivityViewModel
    private lateinit var viewModelFactory: NfcActivityViewModelFactory

    //nfc get tag variable
    private var tag: Tag?  = null //Get Tag
    private lateinit var ndefTag: Ndef //Ndef Tag parsing
    private var ndefSize by Delegates.notNull<Int>() //Tag Size
    private var writable by Delegates.notNull<Boolean>() //Writable
    private lateinit var type: String //Tag Type
    private lateinit var id: String //Tag Id
    private var payloadStr: String = "" //payload String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this@SpotActivity, R.layout.activity_spot)
        viewModelFactory = NfcActivityViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(NfcActivityViewModel::class.java)

        val yesOrNoDialogFragment = YesOrNoDialogFragment()

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
        }

        with(viewModel) {
            if(payloadStr != ""){
                getPayloadStr(payloadStr)
                getQuiz()
                Toast.makeText(applicationContext, "data: ${youtubeData.value}", Toast.LENGTH_LONG).show()
                yesOrNoDialogFragment.show(supportFragmentManager, youtubeData.value)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val message = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES) ?: return

        for(i in 0..message.size){
            setReadTagData(message[0] as NdefMessage?)
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

    //readTagData
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