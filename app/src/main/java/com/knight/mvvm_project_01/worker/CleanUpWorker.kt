package com.knight.mvvm_project_01.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.knight.mvvm_project_01.common.BaseConstant.OUTPUT_PATH
import com.knight.mvvm_project_01.utils.makeStatusNotification
import java.io.File


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 19:13
 * @descript:清理临时文件的Worker
 */
class CleanUpWorker(ctx: Context, params:WorkerParameters) : Worker(ctx,params) {




    override fun doWork(): Result {

        makeStatusNotification("Cleaning up old temporary files", applicationContext)

        return try {
            //删除逻辑
            val outputDirectory = File(applicationContext.filesDir,OUTPUT_PATH)
            if(outputDirectory.exists()){
                val entries = outputDirectory.listFiles()
                if(entries != null){
                   for(entry in entries){
                       val name = entry.name
                       if(name.isNotEmpty() && name.endsWith(".png")){
                           val deleted = entry.delete()
                           Log.i(TAG, String.format("Deleted %s - %s", name, deleted))
                       }
                   }
                }
            }
            // 成功时返回
            Result.success()
        } catch (exception:Exception){
            Log.e(TAG, "Error cleaning up", exception)
            //失败时返回
            Result.failure()
        }




    }

    private val TAG by lazy {
        this::class.java.simpleName
    }









}