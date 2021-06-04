package com.dpfht.testtmdb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dpfht.testtmdb.activity.GenreActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val isTest = false
    if (isTest) {
      Observable.empty<String>()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(object : Observer<Any> {
          override fun onSubscribe(d: Disposable) {}
          override fun onNext(value: Any) {}

          override fun onError(e: Throwable) {
            e.printStackTrace()
          }

          override fun onComplete() {
            testHit()
          }
        })
    } else {
      val itn = Intent(this, GenreActivity::class.java)
      startActivity(itn)

      finish()
    }
  }

  private fun testHit() {
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
}