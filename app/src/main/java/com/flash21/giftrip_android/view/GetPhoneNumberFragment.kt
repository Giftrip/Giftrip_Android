package com.flash21.giftrip_android.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentGetPhonenumberBinding
import com.flash21.giftrip_android.model.confirmNumberData.ConfirmNumberResponse
import com.flash21.giftrip_android.network.RetrofitClient
import com.flash21.giftrip_android.viewmodel.GetPhoneNumberFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.GetPhoneNumberFragmentViewModelFactory
import retrofit2.Call
import retrofit2.Response

class GetPhoneNumberFragment : Fragment() {
    private lateinit var dataBinding: FragmentGetPhonenumberBinding
    private lateinit var viewModel: GetPhoneNumberFragmentViewModel
    private lateinit var viewModelFactory: GetPhoneNumberFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_get_phonenumber, container, false)
        viewModelFactory = GetPhoneNumberFragmentViewModelFactory()
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(GetPhoneNumberFragmentViewModel::class.java)

        dataBinding.btnNext.setOnClickListener {
            if (dataBinding.etPhonenumber.text.toString().length == 11){
                getConfirmNumber()
            }else{
                Toast.makeText(activity, "올바르지 않은 전화번호 형식입니다.\n 010xxxxxxxx로 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        return dataBinding.root
    }
    fun getConfirmNumber(){
        val call: Call<ConfirmNumberResponse> =
            RetrofitClient().postAuth.getConfirmNumber(dataBinding.etPhonenumber.text.toString())
        call.enqueue(object : retrofit2.Callback<ConfirmNumberResponse> {
            override fun onResponse(
                call: Call<ConfirmNumberResponse>,
                response: Response<ConfirmNumberResponse>
            ) {
                if (response.code() == 200){
                    val action =
                        GetPhoneNumberFragmentDirections.actionNavigationGetPhoneNumberToNavigationRegister(
                            dataBinding.etPhonenumber.text.toString()
                        )
                    dataBinding.btnNext.findNavController().navigate(action)
                }else{
                    Toast.makeText(activity, "가입이 완료된 전화번호입니다.\n 다른번호로 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ConfirmNumberResponse>, t: Throwable) {
                Log.e("retrofit Error", t.toString())
            }
        })
    }
}