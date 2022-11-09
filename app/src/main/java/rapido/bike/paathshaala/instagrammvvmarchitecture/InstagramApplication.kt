package rapido.bike.paathshaala.instagrammvvmarchitecture

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.component.DaggerAppComponent

class InstagramApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}