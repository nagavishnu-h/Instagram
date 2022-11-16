package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rapido.bike.paathshaala.instagrammvvmarchitecture.R
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.StoryCard

class StoryAdapter(private val stories: List<StoryCard>): RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.story_card, parent, false))


    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        Glide.with(holder.profilePicture.context).load(story.userPicture).into(holder.profilePicture)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePicture: ImageView = itemView.findViewById(R.id.user_picture_for_story)
    }
}