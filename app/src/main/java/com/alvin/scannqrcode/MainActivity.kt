package com.alvin.scannqrcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanFragment = ScanFragment()

        supportFragmentManager.beginTransaction().replace(R.id.flFragment, ScanFragment()).commit()
    }
}