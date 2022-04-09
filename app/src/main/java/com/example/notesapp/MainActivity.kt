package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivityMainBinding
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val scanQrCode = registerForActivityResult(ScanQRCode(), ::handleResult)

    fun handleResult(result: QRResult) {
        var x=1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gotoqrcode.setOnClickListener{
//            val intent = Intent(this, ScanQRCode::class.java)
//            startActivity(intent)
            scanQrCode.launch(null)
        }

    }
}