package com.example.launchablereplacement.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.launchablereplacement.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

  private lateinit var _binding: FragmentSecondBinding
  private val binding get() = _binding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentSecondBinding.inflate(inflater)
    return binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    binding.saveUserInput.setOnClickListener {
      if (!binding.userInput.editText!!.text.isNullOrBlank()) {
        setFragmentResult(
          FirstFragment.REQUEST_KEY,
          bundleOf(DATA_KEY to binding.userInput.editText!!.text.toString())
        )
        parentFragmentManager.popBackStack()
      } else {
        binding.userInput.error = "user input is empty"
      }
    }
  }

  companion object {
    fun newInstance() = SecondFragment()
    const val DATA_KEY = "name"
  }
}