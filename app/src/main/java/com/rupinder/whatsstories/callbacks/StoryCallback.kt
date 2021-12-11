package com.rupinder.whatsstories.callbacks

import android.view.View
import com.rupinder.whatsstories.models.Story

interface StoryCallback {

    fun onStoryClicked(v: View, story: Story)

}