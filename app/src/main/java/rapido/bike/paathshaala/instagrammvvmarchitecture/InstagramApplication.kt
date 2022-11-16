package rapido.bike.paathshaala.instagrammvvmarchitecture

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.component.DaggerAppComponent

class InstagramApplication : DaggerApplication() {
    init {
        instance = this
    }
    companion object {
        private var instance: InstagramApplication? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}