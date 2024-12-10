package com.kinsin.loadingdialog.ext

import android.view.View
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.animation.LinearInterpolator

/**
 * 开始旋转动画的拓展方法
 * @param speed 2f代表每秒两圈
 */
fun View.startRotate(speed: Float = 2f) {
    // 确保在调用startRotate之前没有其他旋转动画正在进行
    this.clearAnimation()

    // 创建旋转动画，参数是View对象，属性名是"rotation"，动画值是无限循环
    val objectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f)
    objectAnimator.duration = (1000 / speed).toLong() // 设置动画周期，与速度成反比
    objectAnimator.repeatCount = ObjectAnimator.INFINITE // 设置动画无限循环
    objectAnimator.repeatMode = ObjectAnimator.RESTART // 每次循环结束时重新开始动画
    objectAnimator.interpolator = LinearInterpolator() // 设置匀速插值器
    objectAnimator.start() // 开始动画
}

/**
 * 停止旋转动画的拓展方法
 */
fun View.stopRotate() {
    // 获取当前View上的所有动画，并停止它们
    this.animation?.let {
        it.cancel() // 取消动画
        this.clearAnimation() // 清除动画
    }
}

