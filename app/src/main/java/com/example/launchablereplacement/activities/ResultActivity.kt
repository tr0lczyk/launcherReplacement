package com.example.launchablereplacement.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
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

    binding.contractResultButton.setOnClickListener {
      val intent = Intent().apply {
        putExtra(TITLE, "result from contract")
      }
      setResult(RESULT_OK, intent)
      finish()
    }
  }

  class ResultActivityContract : ActivityResultContract<Int, String?>() {

    override fun createIntent(context: Context, input: Int): Intent {
      return getIntent(context, input)
      }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
      val data = intent?.getStringExtra(TITLE)
      return if (resultCode == RESULT_OK && data != null) data
      else null
    }
  }

  companion object {
    const val KEY_RESULT = "RESULT"
    const val TITLE = "title"
    const val ID = "id"

    fun getIntent(context: Context, resultId: Int): Intent {
      return Intent(context, ResultActivity::class.java).apply {
        putExtra(ID, resultId)
      }
    }
  }
}