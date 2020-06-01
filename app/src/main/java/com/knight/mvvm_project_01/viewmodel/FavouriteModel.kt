package com.knight.mvvm_project_01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.db.repository.ShoeRepository


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 16:08
 * @descript:
 */
class FavouriteModel constructor(shoeRepository: ShoeRepository,userId:Long) : ViewModel(){
    // 鞋子集合的观察类
    val shoes:LiveData<List<Shoe>> = shoeRepository.getShoesByUserId(userId)
}