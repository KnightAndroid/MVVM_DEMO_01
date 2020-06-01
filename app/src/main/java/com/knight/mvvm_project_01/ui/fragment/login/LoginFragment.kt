package com.knight.mvvm_project_01.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.databinding.LoginFragmentBinding
import com.knight.mvvm_project_01.ui.activity.MainActivity
import com.knight.mvvm_project_01.utils.AppPrefsUtils
import com.knight.mvvm_project_01.viewmodel.CustomViewModelProvider
import com.knight.mvvm_project_01.viewmodel.LoginModel

//import kotlinx.android.synthetic.main.login_fragment.*
//Fragment的onCreateView 生命周期中使用时会遇到空指针异常的情况


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:12
 * @descript:
 */
class LoginFragment: Fragment() {


    private val loginModel:LoginModel by viewModels {
        CustomViewModelProvider.providerLoginModel(requireContext())
    }

    private val isEnable : Boolean = false



    lateinit var et_account: EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding : LoginFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.login_fragment,container,false)
        onSubscribeUi(binding)
        return binding.root
    }



    private fun onSubscribeUi(binding : LoginFragmentBinding){
        binding.model = loginModel
        binding.isEnable = isEnable
        binding.activity = activity

        binding.btnLogin.setOnClickListener {
            loginModel.login()?.observe(this, Observer { user ->

                   user?.let {
                       AppPrefsUtils.putLong(BaseConstant.SP_USER_ID,it.id)
                       AppPrefsUtils.putString(BaseConstant.SP_USER_NAME,it.account)
                       val intent  = Intent(context, MainActivity::class.java)
                       context!!.startActivity(intent)
                       Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show()
                   } ?: run{
                       Toast.makeText(context, "没有此用户", Toast.LENGTH_SHORT).show()
                   }


            })
        }

        loginModel.p.observe(viewLifecycleOwner, Observer {
            //保证账号和密码不为空的时候才可以登录
            binding.isEnable = it.isNotEmpty() && loginModel.n.value!!.isNotEmpty()
        })

        val name = arguments?.getString(BaseConstant.ARGS_NAME)
        if(!TextUtils.isEmpty(name)){
            loginModel.n.value = name!!

        }

    }



}


//
//var rootView = inflater.inflate(R.layout.login_fragment,container,false)
////通过bundle方式传递参数
//var data = arguments?.getString(BaseConstant.ARGS_NAME) ?:"null"
//et_account = rootView.findViewById(R.id.et_account)
//et_account.setText(data)
//
////?
////列表可以赋值为null
//val nameList :MutableList<String> ? = null
//val size = nameList?.size?:0
//
//
////?.
////nameList?.size
//// 1)====> nameList != null ---> nameList.size()
//// 2)====> nameList == null ---> null
//
////?:
////(nameList?.size)?:0
////1) nameList?.size != null ---> nameList.size()
////2) nameList?.size == null ---> 0
//
//return rootView