package com.flash21.giftrip_android.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentGetPhonenumberBinding
import com.flash21.giftrip_android.viewmodel.GetPhoneNumberFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.GetPhoneNumberFragmentViewModelFactory

class GetPhoneNumberFragment : Fragment(){
    private lateinit var dataBinding: FragmentGetPhonenumberBinding
    private lateinit var viewModel: GetPhoneNumberFragmentViewModel
    private lateinit var viewModelFactory: GetPhoneNumberFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_phonenumber, container, false)
        viewModelFactory = GetPhoneNumberFragmentViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(GetPhoneNumberFragmentViewModel::class.java)

        dataBinding.btnNext.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_get_phoneNumber_to_navigation_register)
        }

        return dataBinding.root
    }
}