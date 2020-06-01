package com.knight.mvvm_project_01.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.knight.mvvm_project_01.db.data.User


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/21 15:15
 * @descript:用户数据库Dao层
 */
@Dao
interface UserDao {
    
    @Query("SELECT * FROM user WHERE user_account = :account AND user_pwd = :pwd")
    fun login(account:String,pwd:String):LiveData<User?>


    @Query("SELECT * FROM user WHERE id=:id")
    fun finduserById(id:Long):LiveData<User>


    @Query("SELECT * FROM user")
    fun getAllUsers():List<User>

    @Insert
    fun insertUser(user:User):Long

    @Delete
    fun deleteuser(user:User)

    @Update
    fun updateUser(user:User)

}