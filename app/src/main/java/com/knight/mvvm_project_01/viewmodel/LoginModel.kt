package com.knight.mvvm_project_01.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knight.mvvm_project_01.common.listener.SimpleWatcher
import com.knight.mvvm_project_01.db.data.User
import com.knight.mvvm_project_01.db.repository.UserRepository


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:21
 * @descript:
 */
class LoginModel constructor(private val repository:UserRepository) : ViewModel() {


    //Observable object,Observable fields,Observalble collections 观察对象 观察字段 观察集合
    val n = MutableLiveData<String>("")
    val p = MutableLiveData<String>("")
    /**
     *
     * 用户名改变回调的函数
     */
    fun onNameChanged(s:CharSequence){
        n.value = s.toString()
    }

    /**
     *
     * 密码改变的回调函数
     *
     */
    fun onPwdChanged(s:CharSequence,start : Int,before : Int,count : Int){
        p.value  = s.toString()
    }


    /**
     * 登录函数
     *
     */
//    fun login(){
//       if(n.get().equals("knight") && p.get().equals("Aa123456")){
//           Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
//           val intent  = Intent(context,MainActivity::class.java)
//           context.startActivity(intent)
//       } else {
//           Toast.makeText(context,"密码不正确",Toast.LENGTH_LONG).show()
//       }
//    }
    /**
     *
     * 登录函数
     */
    fun login(): LiveData<User?>?{
        val pwd = p.value!!
        val account = n.value!!
        return repository.login(account, pwd)
    }


    val nameWatcher = object : SimpleWatcher(){
        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)

            n.value = s.toString()
        }
    }









}