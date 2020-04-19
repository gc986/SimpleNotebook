package ru.gc986.simplenotebook.v.common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ProgressBar

class Dialogs (val context: Context) {

    private var progressBar: Dialog? = null

    fun showProgressBar() {
        if (checkContextNotAlive()) return

        progressBar?.let { return }
        progressBar = Dialog(context).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(ProgressBar(context))
            setCancelable(false)
        }

        progressBar?.show()
    }

    fun hideProgressBar() {
        if (checkContextNotAlive()) return

        progressBar?.let {
            if (it.isShowing)
                it.dismiss()
        }
        progressBar = null
    }

    private fun checkContextNotAlive(): Boolean =
        (context is Activity && (context.isFinishing || context.isDestroyed))

}