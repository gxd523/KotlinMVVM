package com.demo.mvvm.ui.material.drawer.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

abstract class AbsDrawerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val textView = TextView(context)
        textView.text = "This is ${javaClass.simpleName}"
        textView.gravity = Gravity.CENTER
        return textView
    }
}