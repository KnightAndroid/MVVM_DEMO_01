package com.knight.mvvm_project_01.db.repository

import com.knight.mvvm_project_01.db.dao.ShoeDao
import com.knight.mvvm_project_01.db.data.Shoe


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/20 16:07
 * @descript:鞋子仓库
 */
class ShoeRepository private constructor(private val shoeDao: ShoeDao){


    /**
     *
     * 通过id的范围寻找鞋子
     */
    fun getPageShoes(startIndex:Long,endIndex:Long):List<Shoe> = shoeDao.findShoesByIndexRange(startIndex,endIndex)


    fun getAllShoes() = shoeDao.getAllShoesLD()


    /**
     *
     *
     * 通过品牌查询鞋子
     */
    fun getShoesByBrand(brand:Array<String>) = shoeDao.findShoesByBrandLD(brand)


    /**
     *
     * 通过Id查询一双鞋
     *
     */
    fun getShoeById(id:Long) = shoeDao.findShoeByIdLD(id)


    /**
     *
     * 查询用户收藏的鞋
     *
     */
    fun getShoesByUserId(userId:Long) = shoeDao.findShoesByUserId(userId)


    /**
     *
     * 插入鞋子的集合
     */
    fun insertShoes(shoes:List<Shoe>) = shoeDao.insertShoes(shoes)


    
    companion object {
        @Volatile
        private var instance : ShoeRepository? = null
        fun getInstance(shoeDao:ShoeDao):ShoeRepository =
            instance ?: synchronized(this){
                instance
                    ?: ShoeRepository(shoeDao).also {
                        instance = it
                    }
            }

    }


}