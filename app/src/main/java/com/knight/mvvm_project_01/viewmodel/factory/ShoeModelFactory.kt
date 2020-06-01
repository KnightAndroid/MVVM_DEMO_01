package com.knight.mvvm_project_01.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knight.mvvm_project_01.db.repository.ShoeRepository
import com.knight.mvvm_project_01.viewmodel.ShoeModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 15:56
 * @descript:鞋子ShoeModel 工厂
 */
class ShoeModelFactory(private val repository:ShoeRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoeModel(repository) as T
    }
}