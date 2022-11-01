package rapido.bike.paathshaala.instagrammvvmarchitecture.di

import dagger.Component
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.MainActivity

@Component
interface FeedComponent {
    fun inject(activity: MainActivity)
}