package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.view.View
import androidx.core.app.ActivityCompat

internal object Permission {
     fun requestPermissions(context: Context) {
        val permissionId = 42
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            permissionId
        )
    }

    fun View.show(){
        this.visibility =View.VISIBLE
    }

    fun View.hide(){
        this.visibility = View.GONE
    }
}