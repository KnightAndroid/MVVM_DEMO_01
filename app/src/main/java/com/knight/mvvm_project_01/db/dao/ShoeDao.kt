package com.knight.mvvm_project_01.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.knight.mvvm_project_01.db.data.Shoe


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/20 16:08
 * @descript:鞋子的方法
 */
@Dao
interface ShoeDao {




    //通过鞋子的范围寻找Index
    @Query("SELECT * FROM shoe WHERE id between :startIndex AND :endIndex ORDER BY id ASC")
    fun findShoesByIndexRange(startIndex:Long,endIndex:Long):List<Shoe>

    //增加一双鞋子
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe)



    //增加多双鞋子
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoes(shoes: List<Shoe>)


    //删除一双鞋子
    @Delete
    fun deleteShoe(shoe:Shoe)


    //删除多个鞋子 参数是数组
    @Delete
    fun deleteShoes(shoes:List<Shoe>)


    //更新一双鞋子
    @Update
    fun updateShoe(shoe:Shoe)

    //更新多双鞋子
    @Update
    fun updateShoes(shoes:Array<Shoe>)


    //查询一个
    @Query("SELECT * FROM shoe WHERE id=:id")
    fun findShoeById(id:Long):Shoe?


    //查询多个
    @Query("SELECT * FROM shoe WHERE shoe_brand=:brand")
    fun findShoesByBrand(brand:String):List<Shoe>

    //模糊查询 排序 同名鞋名查询鞋
    @Query("SELECT * FROM shoe WHERE shoe_name LIKE :name ORDER BY shoe_brand ASC")
    fun findShoesByName(name:String):List<Shoe>


    //配合LiveData 返回所有的鞋子
    @Query("SELECT * FROM shoe")
    fun getAllShoesLD(): LiveData<List<Shoe>>


    //配合LiveData 通过Id查询单款鞋子
    @Query("SELECT * FROM shoe WHERE id=:id")
    fun findShoeByIdLD(id:Long):LiveData<Shoe>


    /**
     * 通过品牌查询鞋子
     *
     *
     */
    @Query("SELECT * FROM shoe WHERE shoe_brand IN (:brand)")
    fun findShoesByBrandLD(brand:Array<String>): androidx.paging.DataSource.Factory<Int,Shoe>



    //根据收藏集合 查询用户喜欢的鞋集合
    @Query("SELECT shoe.id,shoe.shoe_name,shoe.shoe_description,shoe.shoe_price,shoe.shoe_brand,shoe.shoe_imgurl " +
         "FROM shoe " + "INNER JOIN fav_shoe ON fav_shoe.shoe_id = shoe.id WHERE fav_shoe.user_id = :userId")
    fun findShoesByUserId(userId:Long):LiveData<List<Shoe>>

    
    //配合RxJava通过id查询单款鞋子
    //@Query("SELECT * FROM shoe WHERE id=:id")
    //fun findShoeByIdRx(id:Long):Flowable<Shoe>


}