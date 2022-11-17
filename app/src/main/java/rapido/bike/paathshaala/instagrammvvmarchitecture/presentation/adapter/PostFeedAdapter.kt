package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import rapido.bike.paathshaala.instagrammvvmarchitecture.R
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard

class PostFeedAdapter(private val posts: List<PostCard>): Adapter<PostFeedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.post_card, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        Glide.with(holder.profilePicture.context).load(post.userPicture).into(holder.profilePicture)
        Glide.with(holder.postPictures.context).load(post.postPicture).into(holder.postPictures)
        holder.userName.text = post.title
        holder.likeCount.text = holder.likeCount.context.resources.getString(R.string.likes, post.likeCount.toString())
        holder.postDescription.text = holder.postDescription.context.resources.getString(R.string.description, post.title, post.postDescription)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePicture: ImageView = itemView.findViewById(R.id.user_picture)
        val userName: TextView = itemView.findViewById(R.id.username)
        val postPictures: ImageView = itemView.findViewById(R.id.user_post_pics)
        val likeCount:TextView = itemView.findViewById(R.id.like_count)
        val postDescription:TextView = itemView.findViewById(R.id.post_description)
    }
}