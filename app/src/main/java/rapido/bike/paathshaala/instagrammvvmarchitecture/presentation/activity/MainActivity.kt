package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rapido.bike.paathshaala.instagrammvvmarchitecture.databinding.ActivityMainBinding
import rapido.bike.paathshaala.instagrammvvmarchitecture.di.DaggerFeedComponent
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter.PostFeedAdapter
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.viewmodel.FeedViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var feedAdapter: PostFeedAdapter

    @Inject
    lateinit var viewModel: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFeedComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObservers()
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
}
