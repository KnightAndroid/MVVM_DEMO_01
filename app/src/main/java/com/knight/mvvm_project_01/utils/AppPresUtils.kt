package com.knight.mvvm_project_01.utils

import android.content.Context
import android.content.SharedPreferences
import com.knight.mvvm_project_01.common.BaseApplication
import com.knight.mvvm_project_01.common.BaseConstant


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/13 15:13
 * @descript:数据存储 SharedPreferences
 */

object AppPrefsUtils{
    private var sp: SharedPreferences = BaseApplication.context.getSharedPreferences(BaseConstant.TABLE_PREFS,
        Context.MODE_PRIVATE)
    private var ed:SharedPreferences.Editor

    init {
        ed = sp.edit()
    }


    /**
     * Boolean 数据
     *
     */
    fun putBoolean(key:String,value:Boolean){
        ed.putBoolean(key,value)
        ed.commit()
    }


    /**
     *
     * 默认是false
     */
    fun getBoolean(key:String):Boolean{
        return sp.getBoolean(key,true)
    }


    /**
     * String 数据
     *
     */
    fun putString(key:String,value:String){
        ed.putString(key,value)
        ed.commit()
    }

    /**
     * 默认 ""
     *
     */
    fun getString(key:String): String? {
        return sp.getString(key,"")
    }

    /**
     * Int数据
     *
     */
    fun putInt(key:String,value: Int){
        ed.putInt(key,value)
        ed.commit()
    }

    /**
     *
     * 默认 0
     *
     */
    fun getInt(key:String):Int{
        return sp.getInt(key,0)
    }


    /**
     *
     * Long数据
     */
    fun putLong(key:String,value:Long){
        ed.putLong(key,value)
        ed.commit()
    }


    /**
     * 默认 0
     *
     *
     */
    fun getLong(key:String):Long{
       return sp.getLong(key,0)
    }

    /**
     *
     * 存放set数据
     */
    fun putStringSet(key:String,set:Set<String>){
        val localSet = getStringSet(key)?.toMutableSet()

    }

    /**
     * 默认空Set
     *
     */
    fun getStringSet(key:String): Set<String>? {
        val set = setOf<String>()
        return sp.getStringSet(key,set)

    }


    /**
     * 删除key数据
     *
     */
    fun remove(key:String){
      ed.remove(key)
        ed.commit()
    }


}
