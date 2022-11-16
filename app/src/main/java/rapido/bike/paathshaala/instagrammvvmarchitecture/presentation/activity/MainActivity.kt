package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.android.support.DaggerAppCompatActivity
import rapido.bike.paathshaala.instagrammvvmarchitecture.Constants.LOCALITY
import rapido.bike.paathshaala.instagrammvvmarchitecture.Constants.LOCATION_TAG
import rapido.bike.paathshaala.instagrammvvmarchitecture.InstagramApplication
import rapido.bike.paathshaala.instagrammvvmarchitecture.databinding.ActivityMainBinding
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.ViewModelFactory
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.StoryCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter.PostFeedAdapter
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter.StoryAdapter
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.viewmodel.HomePageViewModel
import rapido.bike.paathshaala.instagrammvvmarchitecture.service.LocationTrackingService
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Extensions.hide
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Extensions.show
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Permission
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var feedAdapter: PostFeedAdapter
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var viewModel: HomePageViewModel
    private var locationReceiver: BroadcastReceiver? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validateAndStartService()
        initViewModel()
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        startBroadcast()
    }

    private fun startBroadcast() {
        locationReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                p1?.getStringExtra(LOCALITY)?.let { updateUI(it) }
            }
        }
        LocalBroadcastManager.getInstance(InstagramApplication.applicationContext())
            .registerReceiver(
                locationReceiver as BroadcastReceiver, IntentFilter(LOCATION_TAG)
            )
    }

    private fun updateUI(location: String) {
        binding.locality.text = location
    }

    private fun validateAndStartService() {
        if (checkPermissions()) {
            startService(Intent(this, LocationTrackingService::class.java))
        } else {
            Permission.requestPermissions(this)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[HomePageViewModel::class.java]
    }

    private fun setUpObservers() {
        viewModel.getFeeds().observe(this) { feedResponse ->
            when (feedResponse) {
                is Resource.Success -> {
                    viewModel.getStories().observe(this) { storyResponse ->
                        when (storyResponse) {
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

    override fun onPause() {
        super.onPause()
        locationReceiver?.let {
            LocalBroadcastManager.getInstance(InstagramApplication.applicationContext())
                .unregisterReceiver(
                    it
                )
        }
        locationReceiver = null
    }
}
