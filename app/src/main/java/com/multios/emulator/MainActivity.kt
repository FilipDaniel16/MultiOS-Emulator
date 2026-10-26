package com.multios.emulator

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

private lateinit var root: LinearLayout
private lateinit var systemName: TextView
private lateinit var status: TextView

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    showHome()
}

private fun showHome() {
    root = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        setPadding(24, 24, 24, 24)
        setBackgroundColor(Color.rgb(25, 28, 40))
    }

    val title = TextView(this).apply {
        text = "📱 MultiOS Emulator"
        textSize = 27f
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
    }

    systemName = TextView(this).apply {
        text = "Choose an operating system"
        textSize = 21f
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
        setPadding(0, 32, 0, 20)
    }

    root.addView(title)
    root.addView(systemName)

    addSystemButton("Android")
    addSystemButton("Xiaomi HyperOS")
    addSystemButton("iOS")
    addSystemButton("Samsung One UI")

    addButton("⛶ Fullscreen") {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    addButton("⚪ Emulator Controls") {
        Toast.makeText(
            this,
            "Emulator controls will be added next.",
            Toast.LENGTH_SHORT
        ).show()
    }

    status = TextView(this).apply {
        text = "Ready · No virtual machine running"
        textSize = 14f
        setTextColor(Color.LTGRAY)
        gravity = Gravity.CENTER
        setPadding(0, 24, 0, 0)
    }

    root.addView(status)
    setContentView(root)
}

private fun addSystemButton(name: String) {
    addButton(name) {
        systemName.text = name

        status.text =
            "$name selected · VM not started"

        Toast.makeText(
            this,
            "A compatible system image and QEMU engine are required.",
            Toast.LENGTH_LONG
        ).show()
    }
}

private fun addButton(
    label: String,
    action: () -> Unit
) {
    val button = Button(this).apply {
        text = label
        textSize = 16f
        isAllCaps = false
        setOnClickListener { action() }
    }

    root.addView(
        button,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    )
}

}
