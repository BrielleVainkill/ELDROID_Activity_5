package com.banquil.activity5

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.banquil.activity5.databinding.ActivityManagepermsBinding
import com.bumptech.glide.Glide
import android.view.View

class ManagePermsActivity:ComponentActivity() {

    private val CAMERA_PERMISSION_CODE = 100
    private val INTERNET_PERMISSION_CODE = 101
    private val VIBRATE_PERMISSION_CODE = 102
    private lateinit var binding: ActivityManagepermsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManagepermsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraPermButton.setOnClickListener{
            if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                loadCameraImage();
                binding.imageV1.visibility = View.VISIBLE
            }
            else {
                requestPermissions(
                    arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE
                )
            }
        }

        binding.internetPermButton.setOnClickListener{
            if (ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {

                loadInternetImage();
                binding.imageV2.visibility = View.VISIBLE
            }
            else {
                requestPermissions(
                    arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE
                )
            }
        }

        binding.vibratePermButton.setOnClickListener{
            if (ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
                vibrate()
            }
            else {
                requestPermissions(
                    arrayOf(android.Manifest.permission.VIBRATE), VIBRATE_PERMISSION_CODE
                )
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow in your app.

                    //post funni pic from drawables since camera isn't available
                    loadCameraImage();
                }
                else {
                    Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        else if (requestCode == INTERNET_PERMISSION_CODE) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission is granted. Continue the action or workflow in your app.

                //grab a pic from the internet
                loadInternetImage();
            }
            else {
                Toast.makeText(this, "Internet Permission Denied :C", Toast.LENGTH_SHORT).show()
            }
            return
        }
        else if (requestCode == VIBRATE_PERMISSION_CODE) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission is granted. Continue the action or workflow in your app.

                //vibrate for 3 seconds
                vibrate()
            }
            else {
                Toast.makeText(this, "Vibrate Permission Denied", Toast.LENGTH_SHORT).show()
            }
            return
        }
    }

    private fun vibrate()
    {
        // get the VIBRATOR_SERVICE system service
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect1: VibrationEffect =
            VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
        Toast.makeText(this, "vibrating uwu", Toast.LENGTH_SHORT).show()
    }

    private fun loadInternetImage() {
        // URL of the image you want to load
        val imageUrl = "https://media.tenor.com/C3EjExGGzrIAAAAM/kuru-kuru-kururin.gif"

        Glide.with(this)
            .load(imageUrl)
            .into(binding.imageV2);

    }

    private fun loadCameraImage() {
        // URL of the image you want to load
        val imageUrl = "https://i.makeagif.com/media/10-26-2022/oiWvVE.gif"

        Glide.with(this)
            .load(imageUrl)
            .into(binding.imageV1);

    }

}