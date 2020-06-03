package com.knight.mvvm_project_01.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.utils.makeStatusNotification
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/6/3 10:44
 * @descript:存储照片的Worker
 */
class SaveImageToFileWorker(ctx: Context, parameters:WorkerParameters) : Worker(ctx,parameters) {
    private val TAG by lazy {
        SaveImageToFileWorker::class.java.simpleName
    }

    private val Title = "Blurred Image"

    private val dateFormatter = SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z", Locale.getDefault())
    override fun doWork(): Result {
       makeStatusNotification("saving image",applicationContext)

       val resolver = applicationContext.contentResolver

       return try{
           //获取从外部传入的参数
           val resourceUri = inputData.getString(BaseConstant.KEY_IMAGE_URI)
           val bitmap = BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(resourceUri)))
           val imageUri = MediaStore.Images.Media.insertImage(resolver,bitmap,Title,dateFormatter.format(Date()))

           if(!imageUri.isNullOrEmpty()){
               //把属性储存在映射中 也就是
               val output = workDataOf(BaseConstant.KEY_IMAGE_URI to imageUri)
               Result.success(output)
           } else {
               Log.e(TAG, "Writing to MediaStore failed")
               Result.failure()
           }
       } catch (exception:Exception){
           Log.e(TAG, "Unable to save image to Gallery", exception)
           Result.failure()
       }
    }


}