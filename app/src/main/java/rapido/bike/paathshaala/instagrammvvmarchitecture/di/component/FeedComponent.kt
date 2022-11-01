package rapido.bike.paathshaala.instagrammvvmarchitecture.di.component

import dagger.Component
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.module.NetworkModule
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface FeedComponent {
    fun inject(activity: MainActivity)
}