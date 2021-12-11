package com.rupinder.whatsstories.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rupinder.whatsstories.commoners.K
import com.rupinder.whatsstories.models.Story
import com.rupinder.whatsstories.utils.loadUrl
import com.rupinder.whatsstories.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val story = intent.getSerializableExtra(K.STORY) as Story
        image.loadUrl(story.path!!)
    }
}
