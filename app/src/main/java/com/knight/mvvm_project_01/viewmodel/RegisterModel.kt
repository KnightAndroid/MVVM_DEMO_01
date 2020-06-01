package com.knight.mvvm_project_01.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knight.mvvm_project_01.db.repository.UserRepository
import kotlinx.coroutines.launch


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/13 10:38
 * @descript:注册model
 */
class RegisterModel constructor(
    private val repository:UserRepository
) : ViewModel(){

    val n = MutableLiveData<String>("")
    val p = MutableLiveData<String>("")
    val mail = MutableLiveData<String>("")
    val number = MutableLiveData<Long>(-1)


    /**
     * 用户名改变回调的函数
     *
     */
    fun onNameChanged(s:CharSequence){
        n.value = s.toString()
    }

    /**
     *
     * 邮箱改变的时候
     *
     */
    fun onEmailChanged(s:CharSequence){
       mail.value = s.toString()
    }


    /**
     *
     * 密码改变的回调函数
     *
     */
    fun onPwdChanged(s:CharSequence){
       p.value = s.toString()
    }


    fun register(){
        var data:Long = 0
           viewModelScope.launch {
           data = repository.register(mail.value!!,n.value!!,p.value!!)
               number.value = data

        }

        //return data
    }


}
