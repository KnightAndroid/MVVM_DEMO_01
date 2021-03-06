package com.knight.mvvm_project_01.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.knight.mvvm_project_01.db.RepositoryProvider
import com.knight.mvvm_project_01.db.data.Shoe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/21 16:25
 * @descript:WorkManager
 */
class ShoeWorker (context: Context,workerParams:WorkerParameters):CoroutineWorker(context,workerParams){

   private val TAG by lazy{
       ShoeWorker::class.java.simpleName
   }



    //指定Dispatchers
    override val coroutineContext: CoroutineDispatcher get()= Dispatchers.IO


    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open("shoes.json").use {
                JsonReader(it.reader()).use {
                    val shoeType = object : TypeToken<List<Shoe>>() {}.type
                    val shoeList: List<Shoe> = Gson().fromJson(it, shoeType)

                    val shoeDao = RepositoryProvider.providerShoeRepository(applicationContext)
                    shoeDao.insertShoes(shoeList)
                    for (i in 0..2) {
                        for (shoe in shoeList) {
                            shoe.id += shoeList.size
                        }
                        shoeDao.insertShoes(shoeList)
                    }
                    Result.success()
                }

            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

}