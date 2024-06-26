@file:Suppress("UNREACHABLE_CODE")

package rapido.bike.paathshaala.instagrammvvmarchitecture.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.*
import rapido.bike.paathshaala.instagrammvvmarchitecture.Constants.LOCALITY
import rapido.bike.paathshaala.instagrammvvmarchitecture.Constants.LOCATION_TAG
import rapido.bike.paathshaala.instagrammvvmarchitecture.InstagramApplication
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.MainActivity

object LocationTracker {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var latitude: Double? = null
    private var longitude: Double? = null

    @SuppressLint("MissingPermission")
    fun getLastLocation(applicationContext: Context) {
        Permission.requestPermissions(applicationContext)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(applicationContext)
        if (isLocationEnabled(applicationContext)) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    Log.d("Instagram LastLocation:", task.result.toString())
                    latitude = task.result.latitude
                    longitude = task.result.longitude
                } else {
                    Log.w("Instagram", "Failed to get location.")
                }
            }
        } else {
            applicationContext.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }


    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    fun createLocationRequest(applicationContext: Context) {
        val locationRequest = LocationRequest()
        locationRequest.interval = 5000
        locationRequest.priority = Priority.PRIORITY_HIGH_ACCURACY
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(applicationContext)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallBack, Looper.getMainLooper()
        )
    }

    private val locationCallBack = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let {
                val locality = getAddress(it.latitude, it.longitude)
                val intent = Intent("com.instagram.LOCATION_UPDATES")
                intent.putExtra("Locality", locality)
                LocalBroadcastManager.getInstance(InstagramApplication.applicationContext())
                    .sendBroadcast(Intent(LOCATION_TAG).putExtra(LOCALITY,locality))
            }
        }
    }

    private fun isLocationEnabled(applicationContext: Context): Boolean {
        val locationManager: LocationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(InstagramApplication.applicationContext())
        val list = geocoder.getFromLocation(lat, lng, 1)
        return list[0].locality
    }
}
