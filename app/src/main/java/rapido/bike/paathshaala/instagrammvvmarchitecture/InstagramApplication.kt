package rapido.bike.paathshaala.instagrammvvmarchitecture

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.component.AppComponent
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.component.DaggerAppComponent
import javax.inject.Inject


class InstagramApplication : Application(), HasAndroidInjector {
    lateinit var appComponent : AppComponent

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(){
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}