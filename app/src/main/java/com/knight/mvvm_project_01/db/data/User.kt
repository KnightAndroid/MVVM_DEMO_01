package com.knight.mvvm_project_01.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/21 14:27
 * @descript:用户表
 */


@Entity(tableName = "user")
data class User(
     @ColumnInfo(name = "user_account") val account:String,//账号
     @ColumnInfo(name = "user_pwd") val pwd:String,//密码
     @ColumnInfo(name = "user_name") val name:Float,//账号
     @ColumnInfo(name = "user_url") val headimage:String//头像地址

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}