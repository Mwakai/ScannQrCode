package com.alvin.scannqrcode

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback

@Suppress("DEPRECATION")
class ScanFragment : Fragment() {

    private lateinit var scannerView: CodeScannerView
    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan, container, false)

        scannerView = view.findViewById(R.id.scanner_view)

        // Request camera permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        } else {
            startCodeScanner()
        }

        return view
    }

    private fun requestCameraPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), Constants.CAMERA_PERMISSION_REQUEST)
    }

    private fun startCodeScanner() {
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                navigateToDetailsActivity(it.text)
            }
        }

        codeScanner.startPreview()
    }

    private fun navigateToDetailsActivity(qrCodeData: String) {
        val intent = Intent(requireContext(), QRCodeDetailsActivity::class.java)
        intent.putExtra("qr_code_data", qrCodeData)
        startActivity(intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Constants.CAMERA_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission is granted, start the code scanner
                startCodeScanner()
            } else {
                // Camera permission is denied, handle the situation accordingly (e.g., show a message, disable camera-related functionality)
                // ...
            }
        }
    }

    override fun onResume() {
        codeScanner.startPreview()
        super.onResume()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}
