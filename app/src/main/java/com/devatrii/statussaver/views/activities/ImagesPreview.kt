package com.devatrii.statussaver.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devatrii.statussaver.R
import com.devatrii.statussaver.databinding.ActivityImagesPreviewBinding

class ImagesPreview : AppCompatActivity() {
  private val activity = this
  private val binding by lazy{
        ActivityImagesPreviewBinding.inflate(layoutInflater)
    }

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      binding.apply {

      }

      }
}