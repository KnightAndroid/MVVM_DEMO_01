package com.knight.mvvm_project_01.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.knight.mvvm_project_01.db.data.Shoe


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/20 16:08
 * @descript:鞋子的方法
 */
@Dao
interface ShoeDao {

    //增加一双鞋子
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe)


}