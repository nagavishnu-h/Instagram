package rapido.bike.paathshaala.instagrammvvmarchitecture.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import rapido.bike.paathshaala.instagrammvvmarchitecture.InstagramApplication
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.module.ActivityBuilderModule
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.module.NetworkModule
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, ActivityBuilderModule::class, AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<InstagramApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build(): AppComponent
    }
}