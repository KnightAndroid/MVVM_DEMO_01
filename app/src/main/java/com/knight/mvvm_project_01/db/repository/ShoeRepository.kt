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





    fun getPageShoes(startIndex:Long,endIndex:Long):List<Shoe> = shoeDao.findShoesByIndexRange(startIndex,endIndex)
}