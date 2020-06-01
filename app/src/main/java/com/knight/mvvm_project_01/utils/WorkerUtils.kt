

@file:JvmName("WorkerUtils")
package com.knight.mvvm_project_01.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.common.BaseConstant
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 19:24
 * @descript:文件创建状态通知
 *
 */


fun makeStatusNotification(message:String,context: Context){
   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

       val name = BaseConstant.VERBOSE_NOTIFICATION_CHANNEL_NAME
       val description = BaseConstant.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
       val importance = NotificationManager.IMPORTANCE_HIGH
       val channel = NotificationChannel(BaseConstant.CHANNEL_ID,name,importance)
       channel.description = description

       // Create the notification
       val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
       notificationManager?.createNotificationChannel(channel)
   }


    val builder = NotificationCompat.Builder(context,BaseConstant.CHANNEL_ID)
        .setSmallIcon(R.drawable.common_ic_account)
        .setContentTitle(BaseConstant.NOTIFICATION_TITLE)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    // Show the notification
    NotificationManagerCompat.from(context).notify(BaseConstant.NOTIFICATION_ID,builder.build())
}

/**
 * Method for sleeping for a fixed about of time to emulate slower work
 */
fun sleep() {
    try {
        Thread.sleep(BaseConstant.DELAY_TIME_MILLIS,0)
    } catch (e:InterruptedException){
        Log.e("WorkerUtils",e.message)
    }

}


/**
 *
 *  Blurs the given Bitmap image
 *  @param bitmap Image to blur
 *  @param applicationContext Application context
 *  @retrun Blurred bitmap image
 *
 *
 */
@WorkerThread
fun blurBitmap(bitmap: Bitmap,applicationContext:Context) : Bitmap {


     lateinit var rsContext: RenderScript


     try{
         // Create the output bitmap
         val output = Bitmap.createBitmap(bitmap.width,bitmap.height,bitmap.config)
         // Blur the image
         rsContext = RenderScript.create(applicationContext,RenderScript.ContextType.DEBUG)
         val inAlloc = Allocation.createFromBitmap(rsContext,bitmap)
         val outAlloc = Allocation.createTyped(rsContext,inAlloc.type)
         val theIntrinsic = ScriptIntrinsicBlur.create(rsContext, Element.U8_4(rsContext))

         theIntrinsic.apply {
             setRadius(10f)
             theIntrinsic.setInput(inAlloc)
             theIntrinsic.forEach(outAlloc)
         }
         outAlloc.copyTo(output)
         return output

     } finally {
         rsContext.finish()
     }
}


/**
 * Writes bitmap to a temporary file and returns the Uri for the file
 * @param applicationContext Application context
 * @param bitmap Bitmap to write to temp file
 * @return Uri for temp file with bitmap
 * @throws FileNotFoundException Throws if bitmap file cannot be found
 */
@Throws(FileNotFoundException::class)
fun writeBitmapToFile(applicationContext: Context,bitmap: Bitmap): Uri {
   val name = String.format("blur-filter-output-%s.png", UUID.randomUUID().toString())
   val outputDir = File(applicationContext.filesDir,BaseConstant.OUTPUT_PATH)

   if(!outputDir.exists()){
       outputDir.mkdirs() // should successd
   }

   val outputFile = File(outputDir,name)
   var out:FileOutputStream? = null

   try {
       out = FileOutputStream(outputFile)
       bitmap.compress(Bitmap.CompressFormat.PNG,0,out)
   } finally {
       out?.let {
           try {
               it.close()
           } catch (ignore:IOException){

           }

       }
   }

   return Uri.fromFile(outputFile)


}



