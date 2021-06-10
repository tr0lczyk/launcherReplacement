package com.example.launchablereplacement.activities

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.launchablereplacement.R.string
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  @ApplicationContext val context: Context
) : ViewModel() {

  val permissionStatus =
    MutableLiveData(context.getString(string.initial_text))

  fun onPermissionsResult(result: Boolean) {
    permissionStatus.value =
      if (result) context.getString(string.permission_result_granted) else context.getString(
        string.permission_result_denied
      )
  }
}