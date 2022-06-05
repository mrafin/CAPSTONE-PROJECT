package com.bangkit2022.kulinerin.ui.navigation.camera

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityResultCameraBinding
import com.bangkit2022.kulinerin.helper.rotateBitmap
import com.bangkit2022.kulinerin.ui.food.DetailFoodActivity
import java.io.File

@Suppress("DEPRECATION")
class ResultCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultCameraBinding
    private var myFile: File? = null
    private var isBackCamera: Boolean? = null
    private var selectedImg: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras

        if (bundle != null) {
            // From Camera
            myFile = bundle.getSerializable("picture") as? File
            isBackCamera = bundle.getBoolean("isBackCamera")

            // From Gallery
            selectedImg = bundle.getString("image-uri")

            if (myFile != null && isBackCamera != null) {
                val result = rotateBitmap(BitmapFactory.decodeFile(myFile!!.path), isBackCamera!!)
                binding.previewImg.setImageBitmap(result)
            } else {
                val imageUri: Uri = Uri.parse(selectedImg)
                binding.previewImg.setImageURI(imageUri)
            }
        }

        binding.btnPredict.setOnClickListener {
            val mIntent = Intent(this@ResultCameraActivity, DetailFoodActivity::class.java)
            startActivity(mIntent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.camera -> {
                val moveToCamera = Intent(this, CameraActivity::class.java)
                startActivity(moveToCamera)
                finish()
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }
}