package com.dpfht.testtmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dpfht.testtmdb.activity.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val itn = Intent(this, GenreActivity::class.java)
    startActivity(itn)

    finish()
  }
}