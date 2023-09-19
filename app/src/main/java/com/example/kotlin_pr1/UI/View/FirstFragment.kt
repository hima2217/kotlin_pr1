package com.example.kotlin_pr1.UI.View

import android.Manifest
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin_pr1.R
import com.example.kotlin_pr1.UI.View.FirstFragment.Companion.REQUIRED_PERMISSIONS
import com.example.kotlin_pr1.databinding.FragmentFirstBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private var imageCapture: ImageCapture? = null
    private val FILENAME = "Date.txt"

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH:mm:ss"
        val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root

        val btnCapture = view.findViewById<Button>(R.id.btnCapture)
        val buttonToFragment1 = view.findViewById<Button>(R.id.button_back2)

        requestPermissions()

        btnCapture.setOnClickListener {
            takePhoto()
        }
        buttonToFragment1.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_mainFragment)
        }
        return view
    }

    private fun requestPermissions() {
        activityResultLauncher.launch(FirstFragment.REQUIRED_PERMISSIONS)
    }


    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        { permissions ->
            // Handle Permission granted/rejected
            var permissionGranted = true
            permissions.entries.forEach {
                if (it.key in MainActivity.REQUIRED_PERMISSIONS && it.value == false)
                    permissionGranted = false
            }
            if (!permissionGranted) {
                Toast.makeText(
                    requireContext(),
                    "Permission request denied",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                startCamera()
            }
        }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }

        // Create the directory if it doesn't exist
        val picturesDir = File(requireContext().getExternalFilesDir(null), "Pictures/CameraX-Image")
        if (!picturesDir.exists()) {
            picturesDir.mkdirs()
        }

        val file = File(picturesDir, "$FILENAME")
        file.appendText(name + "\n")

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireContext().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(requireContext(), "Err", Toast.LENGTH_SHORT).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded"
                    Log.d("photo", msg)
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }


}