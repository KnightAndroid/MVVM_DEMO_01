package com.knight.mvvm_project_01.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.db.repository.UserRepository
import com.knight.mvvm_project_01.utils.AppPrefsUtils
import com.knight.mvvm_project_01.worker.BlurWorker
import com.knight.mvvm_project_01.worker.CleanUpWorker
import com.knight.mvvm_project_01.worker.SaveImageToFileWorker
import kotlinx.coroutines.launch


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/27 16:44
 * @descript: 个人model
 */
class MeModel(val userRepository: UserRepository) : ViewModel() {


   //internal 该字段在同一模块下被看到
   internal var imageUri: Uri? = null
   internal var outPutUri: Uri? = null
   internal val outPutWorkInfos: LiveData<List<WorkInfo>>
   private val workManager = WorkManager.getInstance()
   val user = userRepository.findUserById(AppPrefsUtils.getLong(BaseConstant.SP_USER_ID))


    init {
        outPutWorkInfos = workManager.getWorkInfosByTagLiveData(BaseConstant.TAG_OUTPUT)
    }




    internal fun applyBlur(blurLevel:Int){
        var continuation = workManager
            .beginUniqueWork(
                BaseConstant.IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanUpWorker::class.java)
            )

        for(i in 0 until blurLevel){
           val builder = OneTimeWorkRequestBuilder<BlurWorker>()
           if(i == 0){
               builder.setInputData(createInputDataForUri())
           }
           continuation = continuation.then(builder.build())
        }

        //构建约束条件
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true) //非电池低电量
            .setRequiredNetworkType(NetworkType.CONNECTED)//网络连接的情况
            .setRequiresStorageNotLow(true)//存储空间足
            .build()

        //存储图片
        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .setConstraints(constraints)
            .addTag(BaseConstant.TAG_OUTPUT)
            .build()
        continuation = continuation.then(save)
        continuation.enqueue()
    }



    private fun createInputDataForUri(): Data{
        val builder = Data.Builder()
        imageUri?.let{
            builder.putString(BaseConstant.KEY_IMAGE_URI,imageUri.toString())
        }
        return builder.build()
    }


    private fun uriOrNull(uriString:String?): Uri? {
        return if(!uriString.isNullOrEmpty()){
            Uri.parse(uriString)
        } else
            null
    }

    fun cancelWork(){
        workManager.cancelUniqueWork(BaseConstant.IMAGE_MANIPULATION_WORK_NAME)
    }

    //internal 关键字 在同一模块下的任何地方可以看见
    internal fun setImageUri(uri:String?){
        imageUri = uriOrNull(uri)
    }


    internal fun setOutputUri(uri:String?){
        outPutUri = uriOrNull(uri)
        val value = user.value
        value?.headImage = uri!!
        if(value != null){
            viewModelScope.launch {
                userRepository.updateuser(value)
            }

        }
    }





}