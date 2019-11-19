package com.knight.mvvm_project_01.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.knight.mvvm_project_01.R


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:13
 * @descript:
 */
class RegisterFragment : Fragment() {
    lateinit var et_email:EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.register_fragment,container,false)
        et_email = rootView.findViewById(R.id.et_email)
        //navArgs 这里要指定
        // kotlinOptions{
        //    jvmTarget = "1.8"
        // }
        val safeVarargs:RegisterFragmentArgs by navArgs()
        val email = safeVarargs.email
        et_email.setText(email)
        return rootView
    }
}