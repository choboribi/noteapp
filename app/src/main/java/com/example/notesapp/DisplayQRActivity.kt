package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.notesapp.databinding.ActivityDisplayQrBinding
import com.example.notesapp.databinding.ActivityNoteViewBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


class DisplayQRActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayQrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_display_qr)

        binding = ActivityDisplayQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var testString = intent.getStringExtra("test")
//        println(testString)
//        println("another test")
        binding.textView.text=testString
        // reference code for generating qr code
        // https://code.luasoftware.com/tutorials/android/android-generate-qrcode-with-xzing/
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(testString, BarcodeFormat.QR_CODE, 512, 512)

        binding.imageView.setImageBitmap(bitmap)
    }
}