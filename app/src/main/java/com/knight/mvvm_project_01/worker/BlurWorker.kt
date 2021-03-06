package com.knight.mvvm_project_01.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.utils.blurBitmap
import com.knight.mvvm_project_01.utils.makeStatusNotification
import com.knight.mvvm_project_01.utils.writeBitmapToFile


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 21:01
 * @descript:模糊处理的Worker
 */
class BlurWorker(context: Context,params:WorkerParameters): Worker(context,params) {
    private var TAG:String = this::class.java.simpleName

    override fun doWork(): Result {
        val context = applicationContext
        val resourceUri = inputData.getString(BaseConstant.KEY_IMAGE_URI)

        //通知开始处理图片
        makeStatusNotification("Blurring image", context)



        return try {
            //图片处理逻辑
            if(TextUtils.isEmpty(resourceUri)){
                Log.e(TAG, "Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }

            val resolver = context.contentResolver
            val picture = BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(resourceUri)))

            //创建Bitmap文件
            val output = blurBitmap(picture,context)
            //存入路径
            val outputUri = writeBitmapToFile(context,output)

            //输出路径
            val outputData = workDataOf(BaseConstant.KEY_IMAGE_URI to outputUri.toString())

            makeStatusNotification("Output is $outputUri", context)
            Result.success(outputData)
        } catch (throwable:Throwable){
            Log.e(TAG, "Error applying blur", throwable)
            Result.failure()
        }

    }



}