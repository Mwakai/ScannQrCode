package com.alvin.scannqrcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class QRCodeDetailsActivity : AppCompatActivity() {

    private lateinit var qrCodeTextView: TextView
    private lateinit var qrDescriptionText: TextView
    private lateinit var qrLocationText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_details)

        qrCodeTextView = findViewById(R.id.qrCode)
        qrDescriptionText = findViewById(R.id.qrDescription)
        qrLocationText = findViewById(R.id.qrlocation)

        val qrCodeData = intent.getStringExtra("qr_code_data")
        qrCodeTextView.text = qrCodeData

    }
}