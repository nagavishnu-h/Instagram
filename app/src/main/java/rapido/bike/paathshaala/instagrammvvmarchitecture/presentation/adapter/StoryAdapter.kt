package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rapido.bike.paathshaala.instagrammvvmarchitecture.databinding.StoryCardBinding
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.StoryCard

class StoryAdapter(private val stories: List<StoryCard>): RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {
    inner class StoryViewHolder(val binding: StoryCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = StoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        Glide.with(holder.binding.userPictureForStory.context).load(story.userPicture).into(holder.binding.userPictureForStory)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

}