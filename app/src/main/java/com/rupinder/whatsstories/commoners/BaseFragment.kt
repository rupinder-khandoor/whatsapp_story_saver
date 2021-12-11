package com.rupinder.whatsstories.commoners


import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import org.jetbrains.anko.longToast

open class BaseFragment : Fragment() {

    open fun toast(message: String) {
        activity?.longToast(message)
    }

    // Check if user has granted storage permission
    fun storagePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

}
