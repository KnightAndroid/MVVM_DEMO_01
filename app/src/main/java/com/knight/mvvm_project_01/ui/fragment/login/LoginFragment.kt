package com.knight.mvvm_project_01.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.common.BaseConstant
//import kotlinx.android.synthetic.main.login_fragment.*
//Fragment的onCreateView 生命周期中使用时会遇到空指针异常的情况


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:12
 * @descript:
 */
class LoginFragment: Fragment() {

    lateinit var et_account: EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.login_fragment,container,false)
        //通过bundle方式传递参数
        var data = arguments?.getString(BaseConstant.ARGS_NAME) ?:"null"
        et_account = rootView.findViewById(R.id.et_account)
        et_account.setText(data)
        return rootView
    }



}