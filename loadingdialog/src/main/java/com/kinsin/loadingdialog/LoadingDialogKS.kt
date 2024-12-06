package com.kinsin.loadingdialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.kinsin.demobt.ext.startRotate
import com.kinsin.demobt.ext.stopRotate
import com.kinsin.loadingdialog.databinding.DialogLoadingLayoutKinsinLoadingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadingDialogKS(
    context: Context,
    private val _style: Style = Style.LIGHT1,
    private val alphaValue: Float = DEFAULT_ALPHA_VALUE,
    private val delayAutoDismiss: Long = DELAY_AUTO_DISMISS_DURATION,
    private val textContent: String = DEFAULT_CONTENT,
    private val _isUseCustom: Boolean = false, // 是否启用custom参数
    private val _customIcon: Int = -1, // 自定义图标-优先级高于_style
    private val _customTextColor: Int = -1, // 自定义文字颜色-优先级高于_style
    private val _customBg: Int = -1 // 自定义背景-优先级高于_style
) : Dialog(context) {

    private lateinit var _binding: DialogLoadingLayoutKinsinLoadingBinding
    private var _autoDismissJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogLoadingLayoutKinsinLoadingBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false) // 不可取消
        initView()
        initAction()
    }

    private fun initAction() {
        setOnShowListener {
            // 动画
            _binding.loadingIconKinsinLoading.startRotate(0.5f)
            // 开启自动隐藏
            startAutoDismiss()
        }

        setOnDismissListener {
            _binding.loadingIconKinsinLoading.stopRotate()
            stopAutoDismiss()
        }
    }

    /**
     * 开始自动隐藏
     */
    private fun startAutoDismiss() {
        _autoDismissJob?.cancel()
        _autoDismissJob = CoroutineScope(Dispatchers.IO).launch {
            delay(delayAutoDismiss)
            withContext(Dispatchers.Main) {
                dismiss()
            }
        }
    }

    /**
     * 停止自动隐藏
     */
    private fun stopAutoDismiss() {
        _autoDismissJob?.cancel()
        _autoDismissJob = null
    }

    private fun initView() {
        if (_isUseCustom) {
            _binding.rootAnchorKinsinLoading.setBackgroundResource(_customBg)
            _binding.loadingTextKinsinLoading.setTextColor(_customTextColor)
            _binding.loadingIconKinsinLoading.setImageResource(_customIcon)
            _binding.loadingTextKinsinLoading.text = textContent
        } else {
            _binding.rootAnchorKinsinLoading.setBackgroundResource(_style.bg)
            _binding.loadingTextKinsinLoading.setTextColor(_style.textColor)
            _binding.loadingIconKinsinLoading.setImageResource(_style.img)
            _binding.loadingTextKinsinLoading.text = textContent
        }

        // 透明度设置
        _binding.rootAnchorKinsinLoading.alpha = alphaValue
    }

    enum class Style(
        val bg: Int = R.drawable.white_corner_bg_kinsin_loading,
        val textColor: Int = Color.parseColor("#000000"),
        val img: Int = R.drawable.ic_loading_black_kinsin_loading
    ) {
        LIGHT1(
            R.drawable.white_corner_bg_kinsin_loading,
            Color.parseColor("#000000"),
            R.drawable.ic_loading_black_kinsin_loading
        ),
        DARK1(
            R.drawable.black_corner_bg_kinsin_loading,
            Color.parseColor("#FFFFFF"),
            R.drawable.ic_loading_white_kinsin_loading
        ),
        LIGHT2(
            R.drawable.white_corner_bg_kinsin_loading,
            Color.parseColor("#000000"),
            R.drawable.ic_loading2_black_kinsin_loading
        ),
        DARK2(
            R.drawable.black_corner_bg_kinsin_loading,
            Color.parseColor("#FFFFFF"),
            R.drawable.ic_loading2_white_kinsin_loading
        ),
        LIGHT3(
            R.drawable.white_corner_bg_kinsin_loading,
            Color.parseColor("#000000"),
            R.drawable.ic_loading3_black_kinsin_loading
        ),
        DARK3(
            R.drawable.black_corner_bg_kinsin_loading,
            Color.parseColor("#FFFFFF"),
            R.drawable.ic_loading3_white_kinsin_loading
        ),
    }

    companion object {
        private const val DEFAULT_ALPHA_VALUE = 1f
        private const val DELAY_AUTO_DISMISS_DURATION = 10 * 1000L
        private const val DEFAULT_CONTENT = "加载中..."
    }

}