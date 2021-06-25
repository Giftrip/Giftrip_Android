package com.flash21.giftrip_android.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.databinding.BottomSheetBinding
import com.flash21.giftrip_android.databinding.FragmentHomeBinding
import com.flash21.giftrip_android.model.bottomSheet.BottomSheetAdapter
import com.flash21.giftrip_android.model.bottomSheet.TouchHelperCallBack
import com.flash21.giftrip_android.model.sharedPreference.MyApplication
import com.flash21.giftrip_android.viewmodel.HomeFragmentViewModel
import com.flash21.giftrip_android.viewmodel_factory.HomeFragmentViewModelFactory
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED


/*
* 담당자 : 한승재
* 생성일자 : 2021.05.13.
* 최근 수정일: 2021.05.13.
*
*/
interface SwipeCallBack {
    fun swipeMap(position: Int, bottomSheetAdapter: BottomSheetAdapter)
}

class HomeFragment : Fragment(), OnMapReadyCallback, SwipeCallBack {

    private lateinit var dataBinding: FragmentHomeBinding //HomeFragment DataBinding 객체
    private lateinit var viewModel: HomeFragmentViewModel //HomeFragment viewModel 객체
    private lateinit var viewModelFactory: HomeFragmentViewModelFactory //HomeFragment viewModel Factory 객체
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap
    private val markers = ArrayList<MarkerOptions>()


    //onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bottomSheetAdapter = BottomSheetAdapter(requireContext())
        val accessToken : String = MyApplication.prefs.getString("AccessToken","null")
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModelFactory = HomeFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
        viewModel.getSpotList(accessToken)
        bottomSheetAdapter.notifyDataSetChanged()
        dataBinding.bottomSheetLayout.bottomSheetRecyclerView.adapter = bottomSheetAdapter
        val lm = LinearLayoutManager(requireContext())
        dataBinding.bottomSheetLayout.bottomSheetRecyclerView.layoutManager = lm
        dataBinding.bottomSheetLayout.bottomSheetRecyclerView.setHasFixedSize(true)
        observeViewModel(bottomSheetAdapter)
        val swipeHelperCallback = TouchHelperCallBack(this, bottomSheetAdapter)
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(dataBinding.bottomSheetLayout.bottomSheetRecyclerView)

        mapView = dataBinding.map
        dataBinding.fragmentHomeLayout.setOnClickListener {
            dataBinding.spotInformationLayout.visibility = View.GONE
        }

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        mapView.setPadding(0, 0, 0, 100)

        dataBinding.apply {

        }

        return dataBinding.root
    }

    private fun observeViewModel(bottomSheetAdapter: BottomSheetAdapter) {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            Log.d("Response data", "Response data : $it")
            bottomSheetAdapter.addItem(it)
            it.content.forEach { content ->
                markers.add(MarkerOptions().apply {
                    position(LatLng(content.lat, content.lon))
                })
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val frame = requireView().findViewById<FrameLayout>(R.id.spot_information_layout)
        map = googleMap
        map.setPadding(0,0,0,95)

        map.setOnMapClickListener {
            frame.visibility = View.GONE
        }
        val latLng = LatLng(35.8569652, 128.5885074)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        markers.forEach { marker ->
            map.addMarker(marker.title("xpt"))
        }

    }

    override fun swipeMap(position: Int, bottomSheetAdapter: BottomSheetAdapter) {
        Log.d("position", "${bottomSheetAdapter.spotList[position].lat}")
        controlModal(position,bottomSheetAdapter)
    }

    private fun controlModal(position: Int, bottomSheetAdapter: BottomSheetAdapter){
        val frame = requireView().findViewById<FrameLayout>(R.id.spot_information_layout)
        val frameImage = requireView().findViewById<ImageView>(R.id.spot_image)
        val frameTitle = requireView().findViewById<TextView>(R.id.spot_title)
        val frameDescription = requireView().findViewById<TextView>(R.id.spot_description)
        val frameAddress = requireView().findViewById<TextView>(R.id.spot_address)
        val behavior = BottomSheetBehavior.from(
            dataBinding.bottomSheetLayout.customBottomSheet
        )
        behavior.state = STATE_COLLAPSED
        frame?.visibility = View.VISIBLE
        Glide.with(requireContext())
            .load(bottomSheetAdapter.spotList[position].thumbnails.last())
            .format(DecodeFormat.PREFER_ARGB_8888)
            .transform(CenterCrop())
            .override(100,100)
            .into(frameImage)
        frameTitle.text = bottomSheetAdapter.spotList[position].title
        frameDescription.text = bottomSheetAdapter.spotList[position].description
        frameAddress.text = bottomSheetAdapter.spotList[position].address
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