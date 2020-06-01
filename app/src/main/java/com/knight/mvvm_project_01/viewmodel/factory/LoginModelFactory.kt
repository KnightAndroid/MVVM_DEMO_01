package com.knight.mvvm_project_01.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knight.mvvm_project_01.db.repository.UserRepository
import com.knight.mvvm_project_01.viewmodel.LoginModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/28 19:41
 * @descript:登录模型工厂
 */
class LoginModelFactory (
    private val repository:UserRepository,private val context: Context
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginModel(repository) as T
    }

}