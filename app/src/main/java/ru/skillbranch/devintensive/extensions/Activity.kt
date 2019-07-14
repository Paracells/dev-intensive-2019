package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.MainActivity

fun MainActivity.isKeyboardClosed(): Boolean {
    val rootView = this.window.decorView
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val h = rootView.rootView.height
    val kh = h - rect.bottom
    return kh <= h * .15
}

fun MainActivity.isKeyboardOpen(): Boolean {
    val rootView = this.window.decorView
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val h = rootView.rootView.height
    val kh = h - rect.bottom

    return kh > h * .15
}

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }

}


