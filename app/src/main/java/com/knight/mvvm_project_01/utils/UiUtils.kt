package com.knight.mvvm_project_01.utils

import android.content.Context


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/6/1 15:45
 * @descript:
 */
object UiUtils {

    fun dp2px(context: Context,dpValue:Float) : Int{
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun sp2px(context:Context,spValue:Float) : Int{
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

}