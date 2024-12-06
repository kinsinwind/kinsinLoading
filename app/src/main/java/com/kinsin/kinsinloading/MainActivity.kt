package com.kinsin.kinsinloading

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.kinsin.kinsinloading.databinding.ActivityMainLayoutBinding
import com.kinsin.loadingdialog.LoadingDialogKS

class MainActivity : ComponentActivity() {

    private lateinit var _binding: ActivityMainLayoutBinding
    private lateinit var dialogKS: LoadingDialogKS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        dialogKS = LoadingDialogKS(this@MainActivity, _style = LoadingDialogKS.Style.LIGHT3)

        _binding.startBtnKinsinLoading.setOnClickListener {
            dialogKS.show()
        }

        _binding.EndBtnKinsinLoading.setOnClickListener {
            dialogKS.dismiss()
        }
    }
}