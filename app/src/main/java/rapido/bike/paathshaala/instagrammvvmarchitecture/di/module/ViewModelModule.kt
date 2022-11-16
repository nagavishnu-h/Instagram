package rapido.bike.paathshaala.instagrammvvmarchitecture.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.ViewModelFactory
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.ViewModelKey
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.viewmodel.HomePageViewModel

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel::class)
    abstract fun bindsOnGoingOrderViewModel(homePageViewModel: HomePageViewModel): ViewModel
}