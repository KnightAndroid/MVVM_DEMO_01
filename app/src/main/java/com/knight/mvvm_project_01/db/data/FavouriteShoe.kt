package com.knight.mvvm_project_01.db.data

import androidx.room.*
import java.util.*


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/21 14:38
 * @descript:鞋子
 */

@Entity(
   tableName = "fav_shoe",foreignKeys = [ForeignKey(entity = Shoe::class,parentColumns = ["id"],childColumns = ["shoe_id"]),
                                         ForeignKey(entity = User::class,parentColumns = ["id"],childColumns = ["user_id"])
    ],indices = [Index("shoe_id")]

)
data class FavouriteShoe(
    @ColumnInfo(name = "shoe_id") val shoeId:Long, //外键 鞋子的Id
    @ColumnInfo(name = "user_id") val userid:Long, //外键 用户的id
    @ColumnInfo(name = "fav_date") val date: Calendar //创建日期
) {
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "id")
   var id:Long = 0
}