package rapido.bike.paathshaala.instagrammvvmarchitecture.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.view.View
import androidx.core.app.ActivityCompat
import rapido.bike.paathshaala.instagrammvvmarchitecture.Constants

internal object Permission {
     fun requestPermissions(context: Context) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            Constants.PERMISSION_ID
        )
    }
}