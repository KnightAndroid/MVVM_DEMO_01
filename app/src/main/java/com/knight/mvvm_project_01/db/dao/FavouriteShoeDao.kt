package com.knight.mvvm_project_01.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.knight.mvvm_project_01.db.data.FavouriteShoe


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/21 15:23
 * @descript:收藏记录的Dao
 */


@Dao
interface FavouriteShoeDao {




    // 查询用户下面的FavouriteShoe
    @Query("SELECT * From fav_shoe WHERE user_id = :userId")
    fun findFavouriteShoesByUserId(userId:String) : LiveData<List<FavouriteShoe>>

    // 查询单个FavouriteShoe
    @Query("SELECT * FROM fav_shoe WHERE user_id = :userId AND shoe_id = :shoeId")
    fun findFavouriteShoeByUserIdAndShoeId(userId: String,shoeId:Long):LiveData<FavouriteShoe?>

    //插入单个FavouriteShoe
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteShoe(favouriteShoe: FavouriteShoe)


    //删除单个FavouriteShoe
    @Delete
    fun deleteFavouriteShoe(favouriteShoe: FavouriteShoe)


    //删除多个FavouriteShoe
    @Delete
    fun deleteFavouriteShoes(favouriteShoe: FavouriteShoe)
}