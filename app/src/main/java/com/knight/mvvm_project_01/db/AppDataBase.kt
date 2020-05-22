package com.knight.mvvm_project_01.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import com.knight.mvvm_project_01.db.dao.FavouriteShoeDao
import com.knight.mvvm_project_01.db.dao.ShoeDao
import com.knight.mvvm_project_01.db.dao.UserDao
import com.knight.mvvm_project_01.db.data.FavouriteShoe
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.db.data.User


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/21 14:56
 * @descript:数据库文件
 */

@Database(entities = [User::class, Shoe::class, FavouriteShoe::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase :RoomDatabase() {
    //得到UserDao
    abstract fun userDao(): UserDao
    //得到ShoeDao
    abstract fun shoeDao(): ShoeDao
    //得到FavouriteShoeDao
    abstract fun favouriteShoeDao():FavouriteShoeDao


    companion object{
        @Volatile
        private var instance:AppDataBase? = null

        fun getInstance(context: Context):AppDataBase{
            return instance?: synchronized(this){
                instance?:bui
            }


        }

        /**
         * 创建数据库
         *
         */
        private fun builDataBase(context:Context):AppDataBase{
            return Room.databaseBuilder(context,AppDataBase::class.java,"kotlinDemo_database")
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        //读取鞋子的集合
                        val request  = OneTimeWorkRequestBuilder<>()
                    }

                }).build()


        }

    }





}