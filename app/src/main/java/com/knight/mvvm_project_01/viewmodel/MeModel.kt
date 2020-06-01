package com.knight.mvvm_project_01.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.db.repository.UserRepository
import com.knight.mvvm_project_01.utils.AppPrefsUtils
import com.knight.mvvm_project_01.worker.BlurWorker
import com.knight.mvvm_project_01.worker.CleanUpWorker


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
        outPutWorkInfos = workManager.getWorkInfosByTagLiveData(BaseConstant.OUTPUT_PATH)
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
        }
    }


}