package com.example.asynctaskkotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import java.lang.ref.WeakReference
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream

class MyAsyncTask(mainActivity: MainActivity, private val mutableLiveData: MutableLiveData<Bitmap>):
    AsyncTask<String, Void, Bitmap>() {
    private var activity: WeakReference<MainActivity> = WeakReference(mainActivity)

    override fun onPreExecute() {
        super.onPreExecute()
        Log.d("TAG", "Async Task Started")
        val myActivity = activity.get()
        myActivity?.progressBar?.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg params: String?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val inputStream: InputStream = URL(params[0]).openStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        }
        catch (e: Exception) {
        Log.d("TAG", "Something went wrong! \n ${e.message}")
        e.printStackTrace()
    }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        val myActivity = activity.get()
        myActivity!!.progressBar?.visibility = View.INVISIBLE
        myActivity.image.setImageBitmap(result)
        mutableLiveData.value = result
    }
}