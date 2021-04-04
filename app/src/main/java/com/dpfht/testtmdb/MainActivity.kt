package com.dpfht.testtmdb

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dpfht.testtmdb.activity.GenreActivity
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    /*
    AsyncTask.execute {
      val hostname = "api.themoviedb.org"
      val certificatePinner = CertificatePinner.Builder()
              .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
              .build()
      val client = OkHttpClient.Builder()
              .certificatePinner(certificatePinner)
              .build()

      val request: Request = Request.Builder()
              .url("https://$hostname")
              .build()
      client.newCall(request).execute()
    }
    */

    val itn = Intent(this, GenreActivity::class.java)
    startActivity(itn)

    finish()
  }
}