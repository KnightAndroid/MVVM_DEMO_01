package com.knight.mvvm_project_01.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.utils.AppPrefsUtils


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:14
 * @descript:欢迎页
 */
class WelcomeFragment : Fragment() {

    //登录按钮
    lateinit var btnLogin:Button
    //注册按钮
    lateinit var btnRegister:Button



   override fun onCreateView(
       inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
       ): View?{
         return inflater.inflate(R.layout.welcome_fragment,container,false)
   }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin = view.findViewById(R.id.btn_login)
        btnRegister = view.findViewById(R.id.btn_register)

        //登录点击事件
        btnLogin.setOnClickListener{
            //设置动画参数
            val navOption = navOptions {
                anim {
                    enter = R.anim.common_slide_in_right
                    exit = R.anim.common_slide_out_left
                    popEnter = R.anim.common_slide_in_left
                    popExit = R.anim.common_slide_out_right
                }
            }

            val name = AppPrefsUtils.getString(BaseConstant.SP_USER_NAME)
            // Navigation 传递参数
            val bundle = Bundle()
            bundle.putString(BaseConstant.ARGS_NAME,name)
            findNavController().navigate(R.id.login,bundle,navOption)
        }

        //注册事件
        btnRegister.setOnClickListener{
            //利用SafeArgs传递参数
            val action = WelcomeFragmentDirections.actionWelcomeToRegister()
                .setEMATL("TeaOf1994@Gamil.com")
            findNavController().navigate(action)

        }
    }

}