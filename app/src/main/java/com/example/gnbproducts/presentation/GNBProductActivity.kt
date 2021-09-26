package com.example.gnbproducts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gnbproducts.R
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class GNBProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, GNBProductFragment()).commit()
    }
}