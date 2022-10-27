package rapido.bike.paathshaala.instagrammvvmarchitecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rapido.bike.paathshaala.instagrammvvmarchitecture.databinding.ActivityMainBinding
import rapido.bike.paathshaala.instagrammvvmarchitecture.model.PostCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Status.*
import rapido.bike.paathshaala.instagrammvvmarchitecture.viewmodel.FeedViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: PostFeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        setUpUI()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.getFeeds().observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Log.d("Instagram", "Success")
                    retrieveList(it.data)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Instagram", it.message.toString())
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.GONE
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
}
