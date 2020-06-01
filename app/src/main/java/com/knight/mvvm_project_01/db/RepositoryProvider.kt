package com.knight.mvvm_project_01.db

import android.content.Context
import com.knight.mvvm_project_01.db.repository.FavouriteShoeRepository
import com.knight.mvvm_project_01.db.repository.ShoeRepository
import com.knight.mvvm_project_01.db.repository.UserRepository


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/26 17:07
 * @descript:数据仓库提供
 */
object RepositoryProvider {


    /**
     *
     * 得到用户仓库
     */
    fun providerUserRepository(context: Context): UserRepository{
        return UserRepository.getInstance(AppDataBase.getInstance(context).userDao())
    }


    /**
     *
     * 得到鞋的本地仓库
     */
    fun providerShoeRepository(context:Context):ShoeRepository {
        return ShoeRepository.getInstance(AppDataBase.getInstance(context).shoeDao())
    }

    /**
     * 得到收藏记录的仓库
     *
     */
    fun providerFavouriteShoeRepository(context:Context):FavouriteShoeRepository{
        return FavouriteShoeRepository.getInstance(AppDataBase.getInstance(context).favouriteShoeDao())
    }





}