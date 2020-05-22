package com.knight.mvvm_project_01.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/20 16:35
 * @descript:创建鞋表实体
 */

@Entity(tableName = "shoe")
data class Shoe (
    @ColumnInfo(name = "shoe_name") val name:String, //鞋子名称
    @ColumnInfo(name = "shoe_description") val description : String,//鞋子具体描述
    @ColumnInfo(name = "shoe_price") val price:Float,//鞋子价格
    @ColumnInfo(name = "shoe_brand") val brand:String,//鞋子品牌
    @ColumnInfo(name = "shoe_imgUrl") val imageUrl:String//鞋子链接
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long = 0

    fun getPriceStr():String{
        return price.toString()
    }
}