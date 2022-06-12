package com.bangkit2022.kulinerin.ui.navigation.camera

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityResultCameraBinding
import com.bangkit2022.kulinerin.helper.rotateBitmap
import com.bangkit2022.kulinerin.helper.uriToFile
import com.bangkit2022.kulinerin.viewmodel.ResultCameraViewModel
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@Suppress("DEPRECATION")
class ResultCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultCameraBinding
    private lateinit var imageBitmap: Bitmap
    private lateinit var resultPredict: String
    private lateinit var viewModel: ResultCameraViewModel
    private lateinit var progressDialog: ProgressDialog
    private var myFile: File? = null
    private var isBackCamera: Boolean? = null
    private var selectedImg: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundleExtra = intent.extras
        val mBundle = Bundle()
        val bottomSheet = BottomDialogFragment()
        progressDialog = ProgressDialog(this)

        setupViewModel()

        if (bundleExtra != null) {
            // From Camera
            myFile = bundleExtra.getSerializable("picture") as? File
            isBackCamera = bundleExtra.getBoolean("isBackCamera")

            // From Gallery
            selectedImg = bundleExtra.getString("imageUri")

            imageBitmap = if (myFile != null && isBackCamera != null) {
                rotateBitmap(BitmapFactory.decodeFile(myFile!!.path), isBackCamera!!)
            } else {
                val imageUri: Uri = Uri.parse(selectedImg)
                myFile = uriToFile(imageUri, this)

                MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            }

            binding.previewImg.setImageBitmap(imageBitmap)
        }

        binding.btnPredict.setOnClickListener {
            uploadImage()

            lifecycleScope.launchWhenResumed {
                delay(2000)

                mBundle.putString(BottomDialogFragment.EXTRA_DATA, resultPredict)
                bottomSheet.arguments = mBundle
                bottomSheet.show(
                    supportFragmentManager,
                    bottomSheet.tag
                )
            }
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

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[ResultCameraViewModel::class.java]

        viewModel.result.observe(this) {
            val result = it.predict.replace("_", " ")
            resultPredict = result.capitalizeWords()

        }

        viewModel.errText.observe(this) {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_LONG
            ).show()

            if (progressDialog.isShowing) {
                loadingDialog(false)
            }
        }

        viewModel.isLoading.observe(this, ::loadingDialog)
    }

    private fun uploadImage() {
        val img = myFile as File
        val requestImageFile = img.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            img.name,
            requestImageFile
        )

        viewModel.uploadImage(imageMultipart)
    }

    private fun loadingDialog(isShow: Boolean) {
        progressDialog.setMessage(getString(R.string.prompt_loading))
        if (isShow) progressDialog.show() else progressDialog.dismiss()
    }

    private fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { it.capitalize() }
}