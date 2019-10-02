package com.example.asynctaskkotlin

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        viewModel.mutableLiveData.observe(this, Observer<Bitmap> {
            image.setImageBitmap(it)
        })
    }

    fun downloadImage(view: View) {
        viewModel.startAsync(this)
    }
}
