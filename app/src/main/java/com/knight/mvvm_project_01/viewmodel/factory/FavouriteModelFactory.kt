package com.knight.mvvm_project_01.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knight.mvvm_project_01.db.repository.ShoeRepository
import com.knight.mvvm_project_01.viewmodel.FavouriteModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 16:06
 * @descript:
 */
class FavouriteModelFactory (private val repository: ShoeRepository,private val userId:Long) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass:Class<T>): T{
        return FavouriteModel(repository,userId) as T
    }
}