package com.knight.mvvm_project_01.db.repository

import androidx.lifecycle.LiveData
import com.knight.mvvm_project_01.db.dao.FavouriteShoeDao
import com.knight.mvvm_project_01.db.data.FavouriteShoe
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.util.*


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/26 19:04
 * @descript:收藏鞋子仓库
 */
class FavouriteShoeRepository private constructor(private val favouriteShoeDao: FavouriteShoeDao){

    /**
     *
     * 查看某个用户是否有喜欢记录
     *
     */
     fun findFavouriteShoe(userId:Long,shoeId:Long) : LiveData<FavouriteShoe?> = favouriteShoeDao.findFavouriteShoeByUserIdAndShoeId(userId,shoeId)

    /**
     *
     * 收藏一双鞋
     */
    suspend fun createFavouriteShoe(userId:Long,shoeId:Long){
        withContext(IO){
            favouriteShoeDao.insertFavouriteShoe(FavouriteShoe(shoeId,userId, Calendar.getInstance()))
        }
    }



    companion object {
        @Volatile
        private var instance:FavouriteShoeRepository? = null;

        fun getInstance(favouriteShoeDao: FavouriteShoeDao):FavouriteShoeRepository =
            instance ?: synchronized(this){
                instance ?: FavouriteShoeRepository(favouriteShoeDao).also {
                    instance = it
                }
            }

    }


}