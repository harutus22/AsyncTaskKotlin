package com.example.asynctaskkotlin

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel:ViewModel (){
    var mutableLiveData: MutableLiveData<Bitmap> = MutableLiveData()

    fun startAsync(mainActivity: MainActivity){
        val asyncTask = MyAsyncTask(mainActivity, mutableLiveData)
        val text = "https://api.androidhive.info/json/movies/2.jpg"
        asyncTask.execute(text)
    }
}