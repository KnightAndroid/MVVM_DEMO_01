package com.knight.mvvm_project_01.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knight.mvvm_project_01.db.repository.FavouriteShoeRepository
import com.knight.mvvm_project_01.db.repository.ShoeRepository
import com.knight.mvvm_project_01.viewmodel.DetailModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/28 20:13
 * @descript:收藏我的鞋子工厂·
 */
class FavouriteShoeModelFactory(
    private val shoeRepository:ShoeRepository,
    private val favouriteShoeRepository: FavouriteShoeRepository,
    private val shoeId:Long,
    private val userId:Long
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailModel(shoeRepository,favouriteShoeRepository,shoeId,userId) as T
    }

}