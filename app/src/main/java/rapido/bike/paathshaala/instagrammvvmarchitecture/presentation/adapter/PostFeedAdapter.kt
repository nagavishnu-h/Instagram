package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import rapido.bike.paathshaala.instagrammvvmarchitecture.R
import rapido.bike.paathshaala.instagrammvvmarchitecture.databinding.PostCardBinding
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard

class PostFeedAdapter(private val posts: List<PostCard>): Adapter<PostFeedAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: PostCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(posts[position]){
                Glide.with(binding.userPicture.context).load(this.userPicture).into(binding.userPicture)
                Glide.with(binding.userPostPics.context).load(this.postPicture).into(binding.userPostPics)
                binding.username.text = this.title
                binding.likeCount.text = binding.likeCount.context.resources.getString(R.string.likes, this.likeCount.toString())
                binding.postDescription.text = holder.binding.postDescription.context.resources.getString(R.string.description, this.title, this.postDescription)
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}