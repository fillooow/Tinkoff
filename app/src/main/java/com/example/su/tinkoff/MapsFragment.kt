package com.example.su.tinkoff


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.model.DirectionsResult


class MapsFragment : Fragment(), OnMapReadyCallback {

    lateinit var mMapView: MapView
    lateinit var googleMap: GoogleMap

    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var rootView: View = inflater.inflate(R.layout.fragment_maps, container, false)
        mMapView = rootView.findViewById(R.id.mapView)
        mMapView.onCreate(savedInstanceState)
        mMapView.onResume()

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception){
            e.stackTrace
        }

        mMapView.getMapAsync{
            googleMap = it

            if (ContextCompat.checkSelfPermission(activity!!.applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(activity!!.applicationContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
            } else {
                Log.d("TAG", "оч плохо")
            }
            val position = LatLng(56.8410384,60.6500373)
            googleMap.addMarker(MarkerOptions().position(position).title("Ваше местоположение").snippet(""))



            var cameraPosition = CameraPosition.Builder().target(position).zoom(12f).build()
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))


            var moveToPosition = LatLng(56.83824,60.5948483)
            googleMap!!.addMarker(MarkerOptions().position(moveToPosition).title("16:18").snippet("Тестов Пользователь Банкович"))

            moveToPosition = LatLng(56.84324,60.5848423)
            googleMap!!.addMarker(MarkerOptions().position(moveToPosition).title("14:58").snippet("Точковна Мадам Игоревна"))

            moveToPosition = LatLng(56.82324,60.6048423)
            googleMap!!.addMarker(MarkerOptions().position(moveToPosition).title("16:43").snippet("Ктович Кто Ктото"))
            var apiContext = GeoApiContext.Builder()
                    .apiKey("AIzaSyD2XjT6roGFaL2frAJ801r2ygReOr51wbQ")
                    .build()

            var result = DirectionsResult()
            try {
                result = DirectionsApi.newRequest(apiContext)
                        .origin(com.google.maps.model.LatLng(56.8410384, 60.6500373))
                        .destination(com.google.maps.model.LatLng(56.83824, 60.5948483))
                        .waypoints(com.google.maps.model.LatLng(56.8410384, 60.6500373), com.google.maps.model.LatLng( 56.83824, 60.5948483)).await()
            } catch (e: Exception){
                e.printStackTrace()
            }

            var path: List<com.google.maps.model.LatLng> = result.routes[0].overviewPolyline.decodePath()
            var line = PolylineOptions()

            var latLngBuilder = LatLngBounds.Builder()

            for (it in path){
                line.add(LatLng(it.lat, it.lng))
                latLngBuilder.include(LatLng(it.lat, it.lng))
            }

            line.width(16f).color(R.color.colorPrimary)
            googleMap.addPolyline(line)

            var latLngBounds: LatLngBounds = latLngBuilder.build()
            var width = resources.displayMetrics.widthPixels
            var track = CameraUpdateFactory.newLatLngBounds(latLngBounds, width, width, 25)
            googleMap.moveCamera(track)
            Log.d("TAG", "ended")
        }

        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        val moveToPosition = LatLng(56.83824,60.5948483)
        googleMap!!.addMarker(MarkerOptions().position(moveToPosition).title("Destination").snippet("Test destination"))
        var apiContext = GeoApiContext.Builder()
                .apiKey(R.string.api_key.toString())
                .build()

        var result: DirectionsResult? = null
        try {
            result = DirectionsApi.newRequest(apiContext)
                    .origin(com.google.maps.model.LatLng(56.8410384, 60.6500373))
                    .destination(com.google.maps.model.LatLng(56.83824, 60.5948483))
                    .waypoints(com.google.maps.model.LatLng(56.8410384, 60.6500373), com.google.maps.model.LatLng( 56.83824, 60.5948483)).await()
        } catch (e: Exception){
            e.printStackTrace()
        }

        var path: List<com.google.maps.model.LatLng> = result!!.routes[0].overviewPolyline.decodePath()
        var line = PolylineOptions()

        var latLngBuilder = LatLngBounds.Builder()

        for (it in path){
            line.add(LatLng(it.lat, it.lng))
            latLngBuilder.include(LatLng(it.lat, it.lng))
        }

        line.width(16f).color(R.color.colorPrimary)
        googleMap.addPolyline(line)

        var latLngBounds: LatLngBounds = latLngBuilder.build()
        var width = resources.displayMetrics.widthPixels
        var track = CameraUpdateFactory.newLatLngBounds(latLngBounds, width, width, 25)
        googleMap.moveCamera(track)
        Log.d("TAG", "ended")

    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }
}
