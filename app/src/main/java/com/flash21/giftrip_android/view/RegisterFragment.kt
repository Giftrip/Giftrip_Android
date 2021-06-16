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
            val call: Call<RegisterResponse> = RetrofitClient.instance.postAuth.register(
                RegisterRequest(
                    dataBinding.etConfirmNumber.text.toString(),
                    phoneNumber,
                    dataBinding.etPw.text.toString(),
                    dataBinding.etName.text.toString(),
                    dataBinding.btnBirth.text.toString()
                )
            )
            call.enqueue(object : retrofit2.Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    startActivity(Intent(activity, MainActivity::class.java))
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("retrofit Error", t.toString())
                }

            })
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
}