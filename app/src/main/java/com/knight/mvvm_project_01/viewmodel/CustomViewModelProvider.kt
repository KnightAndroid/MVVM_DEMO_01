package com.knight.mvvm_project_01.viewmodel

import android.content.Context
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.db.RepositoryProvider
import com.knight.mvvm_project_01.db.repository.FavouriteShoeRepository
import com.knight.mvvm_project_01.db.repository.ShoeRepository
import com.knight.mvvm_project_01.db.repository.UserRepository
import com.knight.mvvm_project_01.utils.AppPrefsUtils
import com.knight.mvvm_project_01.viewmodel.factory.*


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 16:04
 * @descript:ViewModel内容提供者
 */
object CustomViewModelProvider {

    fun providerRegisterModel(context: Context):RegisterModelFactory{
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return RegisterModelFactory(repository)
    }



    fun providerLoginModel(context:Context):LoginModelFactory{
        val repository : UserRepository = RepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(repository,context)
    }


    fun providerShoeModel(context:Context):ShoeModelFactory{
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(repository)
    }



    fun providerFavouriteModel(context:Context):FavouriteModelFactory{
       val repository:ShoeRepository = RepositoryProvider.providerShoeRepository(context)
       val userId:Long = AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
       return FavouriteModelFactory(repository, userId)
    }


    fun providerMeModel(context:Context): MeModelFactory {
       val repository:UserRepository = RepositoryProvider.providerUserRepository(context)
       return MeModelFactory(repository)
    }


    fun providerDetailModel(context:Context,shoeId:Long,userId:Long) : FavouriteShoeModelFactory{
        val repository:ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        val favouriteShoeRepository:FavouriteShoeRepository = RepositoryProvider.providerFavouriteShoeRepository(context)
        return FavouriteShoeModelFactory(repository,favouriteShoeRepository,shoeId,userId)
    }



}