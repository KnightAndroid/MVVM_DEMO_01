package com.knight.mvvm_project_01.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knight.mvvm_project_01.db.repository.UserRepository
import com.knight.mvvm_project_01.viewmodel.MeModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/28 20:04
 * @descript:个人工厂
 */
class MeModelFactory(private val repository: UserRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MeModel(repository) as T
    }
}