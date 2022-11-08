package rapido.bike.paathshaala.instagrammvvmarchitecture.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.MainActivity

object LocationTracker {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var latitude: String = ""
    var longitude: String = ""

    @SuppressLint("MissingPermission")
    fun getLastLocation(applicationContext: Context) {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(applicationContext)
        if (checkPermissions(applicationContext)) {
            if (isLocationEnabled(applicationContext)) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    if (task.isSuccessful && task.result != null) {
                        Log.d("Instagram LastLocation:", task.result.toString())
                        latitude = task.result.latitude.toString()
                        longitude = task.result.longitude.toString()
                    } else {
                        Log.w("Instagram", "Failed to get location.")
                    }
                }
            } else {
                applicationContext.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        } else {
            MainActivity().requestPermissions()
        }
    }


    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    fun createLocationRequest(applicationContext: Context) {
        val locationRequest = LocationRequest()
        locationRequest.interval = 5
        locationRequest.priority = Priority.PRIORITY_HIGH_ACCURACY
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(applicationContext)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallBack, Looper.getMainLooper()
        )
    }

    private val locationCallBack = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let {
                latitude = it.latitude.toString()
                longitude = it.longitude.toString()
                Log.d(
                    "Instagram",
                    "locationCallBack: Latitude ${it.latitude} and Longitude ${it.longitude}"
                )
            }
        }
    }

    private fun isLocationEnabled(applicationContext: Context): Boolean {
        val locationManager: LocationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(
                    LocationManager.NETWORK_PROVIDER
                )
    }

    private fun checkPermissions(applicationContext: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack)
    }

}
