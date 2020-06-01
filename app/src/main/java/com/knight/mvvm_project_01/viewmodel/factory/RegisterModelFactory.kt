package com.knight.mvvm_project_01.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knight.mvvm_project_01.db.repository.UserRepository
import com.knight.mvvm_project_01.viewmodel.RegisterModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/28 19:22
 * @descript:注册model工厂
 */
class RegisterModelFactory (private val repository:UserRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterModel(repository) as T
    }
}