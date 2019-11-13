package com.knight.mvvm_project_01.common

import android.app.Application
import android.content.Context


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/13 15:25
 * @descript:Application
 */

//在java中允许创建任意的子类并重写方法任意的方法，除非显示的使用了final关键字进行标注。
//而在kotlin的世界里面则不是这样，在kotlin中它所有的类默认都是final的，
//那么就意味着不能被继承，而且在类中所有的方法也是默认是final的，那么就是kotlin的方法默认也不能被重写

open class BaseApplication :Application(){

    companion object{
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = this

    }
}