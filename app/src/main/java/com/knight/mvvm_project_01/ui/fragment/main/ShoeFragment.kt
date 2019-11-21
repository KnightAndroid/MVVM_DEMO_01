package com.knight.mvvm_project_01.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knight.mvvm_project_01.R


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/20 16:48
 * @descript:
 */
class ShoeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?{
        return inflater.inflate(R.layout.shoe_fragment,container,false)
    }
}