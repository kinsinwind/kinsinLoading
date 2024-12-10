package com.kinsin.kinsinloading

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

        val allStyles = LoadingDialogKS.Style.entries.toTypedArray()
        var index: Int = 0

        _binding.preBtnKinsinLoading.setOnClickListener {
            if (index == 0) {
                index = allStyles.size - 1
            } else {
                index--
            }
            dialogKS = LoadingDialogKS(this@MainActivity, _style = allStyles[index], delayAutoDismiss = 2 * 1000)
            dialogKS.show()
        }

        _binding.nextBtnKinsinLoading.setOnClickListener {
            if (index == allStyles.size - 1) {
                index = 0
            } else {
                index++
            }
            dialogKS = LoadingDialogKS(this@MainActivity, _style = allStyles[index], delayAutoDismiss = 2 * 1000)
            dialogKS.show()
        }

    }
}