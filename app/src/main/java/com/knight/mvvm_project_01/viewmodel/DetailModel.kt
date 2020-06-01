package com.knight.mvvm_project_01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knight.mvvm_project_01.db.data.FavouriteShoe
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.db.repository.FavouriteShoeRepository
import com.knight.mvvm_project_01.db.repository.ShoeRepository
import kotlinx.coroutines.launch


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 16:12
 * @descript:鞋子详情模型
 */
class DetailModel constructor(
    shoeRepository: ShoeRepository,
    private val favouriteShoeRepository: FavouriteShoeRepository,
    private val shoeId:Long,
    val userId:Long) : ViewModel(){

    
    // 鞋
    val shoe: LiveData<Shoe> = shoeRepository.getShoeById(shoeId)

    //收藏记录
    val favouriteShoe: LiveData<FavouriteShoe?> = favouriteShoeRepository.findFavouriteShoe(userId, shoeId)


    //收藏一双鞋
    fun favourite(){
        viewModelScope.launch {
            favouriteShoeRepository.createFavouriteShoe(userId,shoeId)
        }
    }
}