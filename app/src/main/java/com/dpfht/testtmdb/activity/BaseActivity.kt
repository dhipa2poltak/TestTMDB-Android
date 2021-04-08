package com.dpfht.testtmdb.activity

import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

open class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var prgDialog: AlertDialog

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return true
    }
}