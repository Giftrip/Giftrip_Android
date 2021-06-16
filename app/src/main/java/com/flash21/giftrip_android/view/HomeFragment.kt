package com.flash21.giftrip_android.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.FragmentHomeBinding
import com.flash21.giftrip_android.model.BottomSheetAdapter
import com.flash21.giftrip_android.model.spotList.SpotContent
import com.flash21.giftrip_android.model.spotList.SpotList

import com.flash21.giftrip_android.network.RetrofitClient
import com.flash21.giftrip_android.viewmodel.HomeFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.HomeFragmentViewModelFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import retrofit2.Retrofit


/*
* 담당자 : 한승재
* 생성일자 : 2021.05.13.
* 최근 수정일: 2021.05.13.
*
* */

class HomeFragment : Fragment(), OnMapReadyCallback{

    private lateinit var dataBinding : FragmentHomeBinding //HomeFragment DataBinding 객체
    private lateinit var viewModel : HomeFragmentViewModel //HomeFragment viewModel 객체
    private lateinit var viewModelFactory : HomeFragmentViewModelFactory //HomeFragment viewModel Factory 객체
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap


    //onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bottomSheetAdapter = BottomSheetAdapter(requireContext())

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModelFactory = HomeFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
        viewModel.getSpotList()
        bottomSheetAdapter.notifyDataSetChanged()
        dataBinding.bottomSheetLayout.bottomSheetRecyclerView.adapter = bottomSheetAdapter
        val lm = LinearLayoutManager(requireContext())
        dataBinding.bottomSheetLayout.bottomSheetRecyclerView.layoutManager = lm
        dataBinding.bottomSheetLayout.bottomSheetRecyclerView.setHasFixedSize(true)
        observeViewModel(bottomSheetAdapter)
        mapView = dataBinding.map
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        dataBinding.apply {
//recyclerview touch helper 구현 필요
        }

        return dataBinding.root
    }
    private fun observeViewModel(bottomSheetAdapter: BottomSheetAdapter){
        viewModel.data.observe(viewLifecycleOwner, Observer {
            Log.d("Response data","Response data : $it")
            bottomSheetAdapter.addItem(it)
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

}