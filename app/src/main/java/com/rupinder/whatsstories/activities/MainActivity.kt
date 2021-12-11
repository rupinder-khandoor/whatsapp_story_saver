package com.rupinder.whatsstories.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import cn.jzvd.JZVideoPlayer
import com.rupinder.whatsstories.commoners.BaseActivity
import com.rupinder.whatsstories.fragments.ImagesFragment
import com.rupinder.whatsstories.fragments.SavedFragment
import com.rupinder.whatsstories.fragments.VideosFragment
import com.rupinder.whatsstories.utils.PagerAdapter
import com.rupinder.whatsstories.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), TabLayout.OnTabSelectedListener {
    private var doubleBackToExit = false

    companion object {
        private const val IMAGES = "IMAGES"
        private const val VIDEOS = "VIDEOS"
        private const val SAVED = "SAVED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViews()

        if (!storagePermissionGranted()) requestStoragePermission()
    }

    //menu items
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId)  {
            R.id.invite -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hey my friend(s) check out this application.You can save whatsapp stories with the help of this application.Download this application and use it. https://play.google.com/store/apps/details?id=com.rupinder.whatsstories")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }
            R.id.rateus -> {
                val i= Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rupinder.whatsstories"))
                startActivity(i)
                return true
            }
            R.id.contactus ->{
                val i= Intent(Intent.ACTION_VIEW, Uri.parse("mailto:rupinderkhandoor0001@gmail.com"))
                startActivity(i)
                return true
            }
            R.id.moreapps ->{
                val i=Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=pub%3A%20Rupinder%20Khandoor&c=apps"))
                startActivity(i)
                return true
            }
            R.id.follow ->{
                val i=Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/jot_____virk/"))
                startActivity(i)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        setupViewPager()
        setupTabs()
    }

    private fun setupViewPager() {
        val adapter = PagerAdapter(supportFragmentManager, this)
        val images = ImagesFragment()
        val videos = VideosFragment()
        val saved = SavedFragment()

        adapter.addAllFrags(images, videos, saved)
        adapter.addAllTitles(IMAGES, VIDEOS, SAVED)

        viewpager.adapter = adapter
        viewpager.offscreenPageLimit = 2
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

    }

    private fun setupTabs() {
        tabs.setupWithViewPager(viewpager)
        tabs.addOnTabSelectedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewpager.setCurrentItem(tab!!.position, true)
    }

    override fun onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return
        }

        if (doubleBackToExit) {
            super.onBackPressed()
        } else {
            toast("Please tap back again to exit")

            doubleBackToExit = true

            Handler().postDelayed({doubleBackToExit = false }, 1500)
        }
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos()
    }

}
