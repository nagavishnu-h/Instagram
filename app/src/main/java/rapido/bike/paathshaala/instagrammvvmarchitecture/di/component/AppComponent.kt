package rapido.bike.paathshaala.instagrammvvmarchitecture.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import rapido.bike.paathshaala.instagrammvvmarchitecture.InstagramApplication
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.module.ActivityBuilderModule
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.module.NetworkModule
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.module.ViewModelModule
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, ActivityBuilderModule::class, AndroidInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build(): AppComponent
    }

    fun inject(instagramApplication: InstagramApplication)
}