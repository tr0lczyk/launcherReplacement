package com.example.launchablereplacement.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.launchablereplacement.databinding.ActivityResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
  private lateinit var _binding: ActivityResultBinding
  private val binding get() = _binding

  override fun onCreate(
    savedInstanceState: Bundle?
  ) {
    super.onCreate(savedInstanceState)
    _binding = ActivityResultBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.successResultButton.setOnClickListener {
      setResult(RESULT_OK, Intent().putExtra(KEY_RESULT, "Success"))
      finish()
    }

    binding.failureResultButton.setOnClickListener {
      setResult(RESULT_OK, Intent().putExtra(KEY_RESULT, "Failure"))
      finish()
    }
  }

  companion object {
    const val KEY_RESULT = "RESULT"
  }
}