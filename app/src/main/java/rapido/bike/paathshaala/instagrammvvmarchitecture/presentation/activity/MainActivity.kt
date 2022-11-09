package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import rapido.bike.paathshaala.instagrammvvmarchitecture.databinding.ActivityMainBinding
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.ViewModelFactory
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter.PostFeedAdapter
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.viewmodel.FeedViewModel
import rapido.bike.paathshaala.instagrammvvmarchitecture.service.LocationTrackingService
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var feedAdapter: PostFeedAdapter
    private lateinit var viewModel: FeedViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validateAndStartService()
        initViewModel()
        setUpUI()
        setUpObservers()
    }

    private fun validateAndStartService() {
        if(checkPermissions()){
            startService(Intent(this, LocationTrackingService::class.java))
        }else{
            requestPermissions()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, LocationTrackingService::class.java))
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FeedViewModel::class.java]
    }

    private fun setUpObservers() {
        viewModel.getFeeds().observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    retrieveList(it.data)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun setUpUI() {
        feedAdapter = PostFeedAdapter(this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = feedAdapter
        }
    }

    private fun retrieveList(feeds: List<PostCard>) {
        feedAdapter.setPosts(feeds)
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

    private fun requestPermissions() {
        val permissionId = 42
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            permissionId
        )
    }
}
