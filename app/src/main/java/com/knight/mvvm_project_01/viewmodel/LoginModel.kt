package com.knight.mvvm_project_01.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import com.knight.mvvm_project_01.ui.activity.MainActivity
import java.io.Serializable


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:21
 * @descript:
 */
class LoginModel constructor(name :String,pwd:String,context:Context) : Serializable {

    companion object {
        const val serialVersionUID = 1L
    }

    //Observable object,Observable fields,Observalble collections 观察对象 观察字段 观察集合
    val n = ObservableField<String>(name)
    val p = ObservableField<String>(pwd)
    var context: Context = context


    /**
     *
     * 用户名改变回调的函数
     */
    fun onNameChanged(s:CharSequence){
        n.set(s.toString())
    }


    /**
     *
     * 密码改变的回调函数
     *
     */
    fun onPwdChanged(s:CharSequence,start : Int,before : Int,count : Int){
        p.set(s.toString())
    }


    /**
     * 登录函数
     *
     */
    fun login(){
       if(n.get().equals("knight") && p.get().equals("Aa123456")){
           Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
           val intent  = Intent(context,MainActivity::class.java)
           context.startActivity(intent)
       } else {
           Toast.makeText(context,"密码不正确",Toast.LENGTH_LONG).show()
       }
    }




}