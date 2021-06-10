package com.example.launchablereplacement.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.launchablereplacement.R
import com.example.launchablereplacement.activities.ResultActivity
import com.example.launchablereplacement.databinding.FragmentFirstBinding
import com.example.launchablereplacement.utilities.Launchable

class FirstFragment : Fragment() {

  private lateinit var _binding: FragmentFirstBinding
  private val binding get() = _binding
  private val clickForResult = registerForActivityResult(StartActivityForResult()){result ->
    if(result.resultCode == AppCompatActivity.RESULT_OK){
      binding.firstFragmentText.text = result.data?.getStringExtra(ResultActivity.KEY_RESULT)
    }
  }


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFirstBinding.inflate(inflater)
    return binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setFragmentResultListener(REQUEST_KEY) { key, bundle ->
      if (key == REQUEST_KEY) {
        val result = bundle.getString(SecondFragment.DATA_KEY)
        binding.firstFragmentText.text = result
      }
    }
    binding.openSecondFragment.setOnClickListener {
      parentFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainerView, SecondFragment.newInstance())
        .addToBackStack("secondFragment")
        .commit()
    }
    binding.clickForFragmentResult.setOnClickListener {
      val launchable = Launchable.Fragment(clickForResult,this)
      launchable.startActivityForResult(Intent(requireContext(), ResultActivity::class.java))
    }
  }

  companion object {
    const val REQUEST_KEY = "1234"
  }
}