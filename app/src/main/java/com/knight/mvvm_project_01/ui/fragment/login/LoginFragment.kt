package com.knight.mvvm_project_01.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.knight.mvvm_project_01.R


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:12
 * @descript:
 */
class LoginFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment,container,false)
    }



}