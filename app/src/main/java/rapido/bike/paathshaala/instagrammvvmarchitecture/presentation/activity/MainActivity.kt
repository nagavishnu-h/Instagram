package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import rapido.bike.paathshaala.instagrammvvmarchitecture.databinding.ActivityMainBinding
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.ViewModelFactory
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.StoryCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.Permission.hide
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity.Permission.show
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter.PostFeedAdapter
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter.StoryAdapter
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.viewmodel.HomePageViewModel
import rapido.bike.paathshaala.instagrammvvmarchitecture.service.LocationTrackingService
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private var locationTrackingService: LocationTrackingService ?= null
    private lateinit var binding: ActivityMainBinding
    private lateinit var feedAdapter: PostFeedAdapter
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var viewModel: HomePageViewModel
//    private  val viewModel1: FeedViewModel ?= null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAddress(12.9100183, 77.6990233)
        validateAndStartService()
        initViewModel()
        setUpObservers()
    }

    private fun validateAndStartService() {
        if(checkPermissions()){
            startService(Intent(this, LocationTrackingService::class.java))
        }else{
            Permission.requestPermissions(this)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[HomePageViewModel::class.java]
    }

    private fun setUpObservers() {
        viewModel.getFeeds().observe(this) { feedResponse->
            when (feedResponse) {
                is Resource.Success -> {
                    viewModel.getStories().observe(this){ storyResponse ->
                        when(storyResponse) {
                            is Resource.Success -> {
                                binding.recyclerViewFeed.show()
                                binding.progressBar.hide()
                                setUpUI(feedResponse.data, storyResponse.data)
                            }
                            is Resource.Failure -> {
                                binding.progressBar.show()
                            }
                            is Resource.Loading -> {
                                binding.progressBar.show()
                            }
                        }
                    }
                }
                is Resource.Failure -> {
                    binding.progressBar.show()
                }
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
            }
        }
    }

    private fun setUpUI(posts: List<PostCard>, stories: List<StoryCard>) {
        feedAdapter = PostFeedAdapter(posts)
        storyAdapter = StoryAdapter(stories)
        binding.recyclerViewFeed.adapter = feedAdapter
        binding.recyclerViewStory.adapter = storyAdapter
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        locationTrackingService = null
    }

    private fun getAddress(lat: Double, lng: Double){
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(lat, lng, 1)
        Log.d("Locality", list[0].locality)
    }
}
