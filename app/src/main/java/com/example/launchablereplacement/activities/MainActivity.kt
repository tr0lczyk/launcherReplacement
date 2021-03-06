package com.example.launchablereplacement.activities

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.launchablereplacement.activities.ResultActivity.ResultActivityContract
import com.example.launchablereplacement.databinding.ActivityMainBinding
import com.example.launchablereplacement.utilities.Launchable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels()
  private lateinit var _binding: ActivityMainBinding
  private val binding get() = _binding

  private val requestPermission = registerForActivityResult(RequestPermission()) { result ->
    viewModel.onPermissionsResult(
      result
    )
  }

  private val clickForResult = registerForActivityResult(StartActivityForResult()){result ->
    if(result.resultCode == RESULT_OK){
      viewModel.permissionStatus.value = result.data?.getStringExtra(ResultActivity.KEY_RESULT)
    }
  }

  private val chooseContractForResult = registerForActivityResult(ResultActivityContract()) { result ->
    viewModel.contractStatus.value = if(result != null) "Result : $result" else "No Result"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    viewModel.permissionStatus.observe(this, {
      binding.status.text = it
    })
    viewModel.contractStatus.observe(this, {
      binding.contractResultStatus.text = it
    })
    binding.permissionButton.setOnClickListener {
      requestPermission.launch(Manifest.permission.CAMERA)
    }
    binding.forResultButton.setOnClickListener {
      val launchable = Launchable.Activity(clickForResult,this)
      launchable.startActivityForResult(Intent(this,ResultActivity::class.java))
    }
    binding.contractResultButton.setOnClickListener {
      chooseContractForResult.launch(1)
    }
    binding.goToFragment.setOnClickListener {
      startActivity(Intent(this,FragmentActivity::class.java))
    }
  }
}