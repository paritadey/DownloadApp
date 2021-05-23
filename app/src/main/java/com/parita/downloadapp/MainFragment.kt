package com.parita.downloadapp

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.parita.downloadapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.content_main.view.*

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var transitionListner : MotionLayout.TransitionListener
    private lateinit var downloadManager: DownloadManager
    private val PERMISSIONCODE: Int = 12
    var list: ArrayList<Long> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        transitionListner = object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                Handler().postDelayed(object : Runnable{
                    override fun run() {
                        binding.downloadButton.motionLayout.button.text = ""
                        binding.downloadButton.motionLayout.transitionToStart()//Using this will create a smooth transition to the initial state.
                        //binding.downloadButton.motionLayout.jumpToState(R.id.start)//Using this will create a direct jump to the initial state with out the animation.
                    }
                }
                ,1000)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        }
        binding.downloadButton.motionLayout.setTransitionListener(transitionListner)
    }
    private fun checkRadioButton(view: View) {
        val selectedRadioButtonId: Int = binding.rgSelector.checkedRadioButtonId
        if (selectedRadioButtonId != -1) {
            var selectedRadioButton: RadioButton = view.findViewById(selectedRadioButtonId)
            val selectedRbText: String = selectedRadioButton.getText().toString()
            downloadCondition(selectedRbText)
        } else {
            Toast.makeText(
                requireContext(),
                "Please select an option to download",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun downloadCondition(select: String) {
        if (select.contains("Glide")) {
            Log.d("TAG", "first is Selected")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_DENIED && (ContextCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.INTERNET
                    ) != PackageManager.PERMISSION_DENIED)
                ) {
                    requestPermissions(
                        arrayOf(
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.INTERNET
                        ), PERMISSIONCODE
                    )
                } else {
                    download()
                }
            }
        } else if (select.contains("LoadApp")) {
            Log.d("TAG", "second is Selected")
        } else if (select.contains("Retrofit")) {
            Log.d("TAG", "third is Selected")
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONCODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    download()
                } else {
                    Snackbar.make(binding.root, "Permission is not granted", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    private fun download() {
        downloadManager =
            requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val Download_Uri =
            Uri.parse("https://i.pinimg.com/564x/75/95/d3/7595d37ebb81c6c2d0b7160b597b082c.jpg")
        val request = DownloadManager.Request(Download_Uri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setAllowedOverRoaming(false)
        request.setTitle(resources.getString(R.string.radio_txt1))
        request.setDescription("Downloading image")
        request.setVisibleInDownloadsUi(true)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "/DownloadApp//SampleApp.png"
        )
        var refid: Long = downloadManager.enqueue(request)

    }
}