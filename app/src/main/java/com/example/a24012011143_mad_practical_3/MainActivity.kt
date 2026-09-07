package com.example.a24012011143_mad_practical_3


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        implicitintent()
        explicitintent()
    }

    fun implicitintent() {

        // Browse
        findViewById<Button>(R.id.btn_browse).setOnClickListener {

            val url = findViewById<EditText>(R.id.editTextText).text.toString()

            Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
                startActivity(it)
            }
        }

        // Call
        findViewById<Button>(R.id.btn_call).setOnClickListener {

            val number = findViewById<EditText>(R.id.editTextText2).text.toString()

            Intent(Intent.ACTION_DIAL).apply {
                data = "tel:$number".toUri()
            }.also {
                startActivity(it)
            }
        }

        // Call Log
        findViewById<Button>(R.id.btn_calllog).setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CALL_LOG
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CALL_LOG),
                    100
                )

            } else {

                openCallLog()

            }
        }

        // Gallery
        findViewById<Button>(R.id.btn_gallery).setOnClickListener {

            Intent(
                Intent.ACTION_VIEW,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ).also {

                startActivity(it)
            }
        }

        // Camera
        findViewById<Button>(R.id.btn_camera).setOnClickListener {

            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {

                startActivity(it)
            }
        }

        // Alarm
        findViewById<Button>(R.id.btn_alarm).setOnClickListener {

            Intent(AlarmClock.ACTION_SET_ALARM).apply {

                putExtra(AlarmClock.EXTRA_HOUR, 7)
                putExtra(AlarmClock.EXTRA_MINUTES, 30)
                putExtra(AlarmClock.EXTRA_MESSAGE, "Wake Up")

            }.also {

                startActivity(it)

            }
        }
    }

    fun explicitintent() {

        findViewById<Button>(R.id.btn_login).setOnClickListener {

            Intent(this, LoginActivity::class.java).also {

                startActivity(it)

            }
        }
    }

    private fun openCallLog() {

        Intent(Intent.ACTION_VIEW, CallLog.Calls.CONTENT_URI).also {

            startActivity(it)

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {

            openCallLog()

        }
    }
}