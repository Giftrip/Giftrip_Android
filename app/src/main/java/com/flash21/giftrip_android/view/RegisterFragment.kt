package com.flash21.giftrip_android.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentRegisterBinding
import com.flash21.giftrip_android.encrypt.EncryptString
import com.flash21.giftrip_android.model.registerData.RegisterRequest
import com.flash21.giftrip_android.model.registerData.RegisterResponse
import com.flash21.giftrip_android.network.RetrofitClient
import com.flash21.giftrip_android.viewmodel.RegisterFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.RegisterFragmentViewModelFactory
import retrofit2.Call
import retrofit2.Response
import java.util.*

class RegisterFragment : Fragment() {
    private lateinit var dataBinding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterFragmentViewModel
    private lateinit var viewModelFactory: RegisterFragmentViewModelFactory

    val args: RegisterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        viewModelFactory = RegisterFragmentViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RegisterFragmentViewModel::class.java)
        val phoneNumber = args.phoneNumber

        dataBinding.btnRegister.setOnClickListener {
            if (dataBinding.etPw.text.toString() == dataBinding.etPwCheck.text.toString()){
                postRegisteValue(phoneNumber)
            }else{
                Toast.makeText(activity, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
            }
        }
        dataBinding.btnBirth.setOnClickListener {
            val cal =
                Calendar.getInstance()
            activity?.let { it1 ->
                DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                    dataBinding.btnBirth.text = "$y-$m-$d"
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show()
            }
        }

        return dataBinding.root
    }
    fun postRegisteValue(phoneNumber : String){
        val call: Call<RegisterResponse> = RetrofitClient.instance.postAuth.register(
            RegisterRequest(
                dataBinding.etConfirmNumber.text.toString(),
                phoneNumber,
                EncryptString().hashSHA256(dataBinding.etPw.text.toString())!!,
                dataBinding.etName.text.toString().replace(" ",""),
                dataBinding.btnBirth.text.toString()
            )
        )
        call.enqueue(object : retrofit2.Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if(response.code() == 200){
                    startActivity(Intent(activity, MainActivity::class.java))
                }else if (response.code() == 401){
                    Toast.makeText(activity, "인증번호 불일치", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("retrofit Error", t.toString())
            }
        })
    }
}