package com.example.launchablereplacement.utilities

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity

interface Launchable {

  fun getContext(): Context

  fun startActivityForResult(
    intent: Intent
  )

  class Activity(
    private val activityResultContract: ActivityResultLauncher<Intent>,
    private val activity: AppCompatActivity,
  ) : Launchable {
    override fun getContext(): Context {
      return activity
    }

    override fun startActivityForResult(
      intent: Intent
    ) {
      activityResultContract.launch(intent)
    }
  }

  class Fragment(
    private val activityResultContract: ActivityResultLauncher<Intent>,
    private val fragment: androidx.fragment.app.Fragment,
  ) : Launchable {
    override fun getContext(): Context {
      return fragment.requireContext()
    }

    override fun startActivityForResult(
      intent: Intent
    ) {
      activityResultContract.launch(intent)
    }
  }
}