package com.rupinder.whatsstories.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rupinder.whatsstories.callbacks.StoryCallback
import com.rupinder.whatsstories.commoners.AppUtils.setDrawable
import com.rupinder.whatsstories.models.Story
import com.rupinder.whatsstories.utils.inflate
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.rupinder.whatsstories.R
import com.rupinder.whatsstories.databinding.ItemStoryBinding

class StoriesAdapter(private val callback: StoryCallback, private val context: Context) : RecyclerView.Adapter<StoriesAdapter.StoriesHolder>(){
    private val stories = mutableListOf<Story>()

    fun addStory(story: Story) {
        stories.add(story)
        notifyItemInserted(stories.size)
    }

    fun addStories(stories: List<Story>) {
        this.stories.addAll(stories)
        notifyDataSetChanged()
    }

    fun clearStories() {
        stories.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesHolder {
        return StoriesHolder(parent.inflate(R.layout.item_story), callback, context)
    }

    override fun getItemCount(): Int = stories.size

    override fun onBindViewHolder(holder: StoriesHolder, position: Int) {
        holder.bind(stories[position])
    }

    class StoriesHolder(private val binding: ItemStoryBinding, private val callback: StoryCallback, private val context: Context): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.videoIcon.setImageDrawable(setDrawable(context, Ionicons.Icon.ion_play, R.color.white, 27))
        }

        fun bind(story: Story) {
            binding.story = story
            binding.callback = callback
        }

    }

}