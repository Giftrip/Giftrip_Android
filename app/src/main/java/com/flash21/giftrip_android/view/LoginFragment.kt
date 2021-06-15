package com.flash21.giftrip_android.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentLoginBinding
import com.flash21.giftrip_android.model.LoginRequest
import com.flash21.giftrip_android.model.LoginResponse
import com.flash21.giftrip_android.network.RetrofitClient
import com.flash21.giftrip_android.viewmodel.LoginFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.LoginFragmentViewModelFactory
import retrofit2.Call
import retrofit2.Response

/*
* 담당자 : 이용재
* 생성일자 : 2021.06.11.
* 최근 수정일: 2021.06.13.
*
* */


class LoginFragment : Fragment() {

    private lateinit var dataBinding: FragmentLoginBinding
    private lateinit var viewModel: LoginFragmentViewModel
    private lateinit var viewModelFactory: LoginFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModelFactory = LoginFragmentViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginFragmentViewModel::class.java)

        dataBinding.btnLogin.setOnClickListener() {
            val call: Call<LoginResponse> = RetrofitClient.instance.postAuth.login(
                LoginRequest(
                    dataBinding.etPhonenumber.text.toString(),
                    dataBinding.etPw.text.toString()
                )
            )
            call.enqueue(object : retrofit2.Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val intent = Intent(activity,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("retrofit error",t.toString())
                }
            })
        }

        return dataBinding.root
    }
}