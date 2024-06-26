package rapido.bike.paathshaala.instagrammvvmarchitecture.utils

import android.view.View

object Extensions {
    fun View.show(){
        this.visibility = View.VISIBLE
    }

    fun View.hide(){
        this.visibility = View.GONE
    }
}