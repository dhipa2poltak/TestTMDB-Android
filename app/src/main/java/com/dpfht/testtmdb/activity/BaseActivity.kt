package com.dpfht.testtmdb.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dpfht.testtmdb.R

open class BaseActivity: AppCompatActivity() {

    lateinit var prgDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setCancelable(false)
        builder.setView(R.layout.dialog_loading)
        prgDialog = builder.create()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return true
    }
}