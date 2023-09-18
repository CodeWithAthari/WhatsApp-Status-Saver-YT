package com.devatrii.statussaver.utils

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce


fun View.slideFromStart() {
    val screenWidth = context.resources.displayMetrics.widthPixels.toFloat()
    val startPositionX = -width.toFloat() - screenWidth // Start position outside the screen on the left
    val endPositionX = x // Current position on the screen

    // Create a TranslateAnimation for sliding in
    val slideAnimation = TranslateAnimation(startPositionX, endPositionX, 0f, 0f)
    slideAnimation.interpolator = AccelerateDecelerateInterpolator()
    slideAnimation.duration = 1000

    // Create an AlphaAnimation for fading in
    val fadeInAnimation = AlphaAnimation(0f, 1f)
    fadeInAnimation.duration = 1500

    // Create an AnimationSet to combine both animations
    val animationSet = AnimationSet(true)
    animationSet.addAnimation(slideAnimation)
    animationSet.addAnimation(fadeInAnimation)

    startAnimation(animationSet)
}

fun View.slideToEndWithFadeOut() {
    val screenWidth = context.resources.displayMetrics.widthPixels.toFloat()
    val startPositionX = x // Current position on the screen
    val endPositionX = screenWidth // End position outside the screen on the right

    // Create a TranslateAnimation for sliding out
    val slideAnimation = TranslateAnimation(startPositionX, endPositionX, 0f, 0f)
    slideAnimation.interpolator = AccelerateDecelerateInterpolator()
    slideAnimation.duration = 1000

    // Create an AlphaAnimation for fading out
    val fadeOutAnimation = AlphaAnimation(1f, 0f)
    fadeOutAnimation.duration = 1500

    // Create an AnimationSet to combine both animations
    val animationSet = AnimationSet(true)
    animationSet.addAnimation(slideAnimation)
    animationSet.addAnimation(fadeOutAnimation)

    startAnimation(animationSet)
}


