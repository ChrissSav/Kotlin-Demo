package com.example.android_kotlin_demo.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.android_kotlin_demo.R
import com.tapadoo.alerter.Alerter
import java.util.regex.Pattern

fun ImageView.loadImageFromUrl(url: String?) {
    if (url != null) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}


fun Activity.createAlerter(msg: String) {
    var icon = R.drawable.ic_exclamation_mark
    if (this.resources.getString(R.string.ERROR_INTERNET_CONNECTION) == msg)
        icon = R.drawable.ic_no_wifi
    Alerter.create(this)
        .setTitle(getString(R.string.warning))
        .setText(msg)
        .setIcon(icon)
        .setBackgroundColorRes(R.color.orange_color)
        .setDuration(1800)
        .enableSwipeToDismiss()
        .show()
}



fun isValidRegex(text: String, regex: String): Boolean {
    val regexPattern = Pattern.compile(regex)
    return regexPattern.matcher(text).matches()
}

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}


fun View.goneAnim() {
    if (visibility != View.GONE)
        animate().alpha(1f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = View.GONE
            }
        })

}

fun View.visibleAnim() {
    if (visibility != View.VISIBLE)
        animate().alpha(1f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })

}

fun CharSequence.getString(): String? {
    return if (this.isEmpty()) null else this.toString().trim()
}

fun CharSequence.getLength(): Int {
    return if (this.isEmpty()) 0 else this.toString().length
}
