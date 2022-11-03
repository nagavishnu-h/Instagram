package rapido.bike.paathshaala.instagrammvvmarchitecture.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}