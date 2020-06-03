package com.knight.mvvm_project_01.ui.fragment.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.databinding.MeFragmentBinding
import com.knight.mvvm_project_01.viewmodel.CustomViewModelProvider
import com.knight.mvvm_project_01.viewmodel.MeModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/20 16:16
 * @descript:
 */
class MeFragment :Fragment() {


    private val TAG by lazy {
        MeFragment::class.simpleName
    }

    private val model: MeModel by viewModels{
        CustomViewModelProvider.providerMeModel(requireContext())

    }

    //选择图片标识
    private val REQUEST_CODE_IMAGE = 100

    //加载框
    private val sweetAlertDialog : SweetAlertDialog by lazy {
        SweetAlertDialog(requireContext(),SweetAlertDialog.PROGRESS_TYPE).setTitleText("头像").setContentText("更新中...")
    }






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?{
        // Data Binding
        val binding:MeFragmentBinding = MeFragmentBinding.inflate(inflater,container,false)
        initListener(binding)
        onSubscribeUi(binding)
        return binding.root
    }


    /**
     * 初始化监听器
     *
     */
    private fun initListener(binding:MeFragmentBinding){
        binding.ivHead.setOnClickListener {
            //选择处理的图片
            val chooseIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(chooseIntent,REQUEST_CODE_IMAGE)
        }

    }




    private fun onSubscribeUi(binding:MeFragmentBinding){
        model.user.observe(this, Observer {
            binding.user = it
        })
        //任务状态的观测
        model.outPutWorkInfos.observe(this, Observer {
            if(it.isNullOrEmpty())
                return@Observer

            val state = it[0]
            if(state.state.isFinished){
                //更新头像
                val outputImageUri = state.outputData.getString(BaseConstant.KEY_IMAGE_URI)
                if(!outputImageUri.isNullOrEmpty()){
                    model.setOutputUri(outputImageUri)
                }
                sweetAlertDialog.dismiss()
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_IMAGE -> data?.let {
                   handleImageRequestResult(data)
                }
                else -> Log.d(TAG, "Unknown request code.")

            }
        } else {
            Log.e(TAG, String.format("Unexpected Result code %s", resultCode))
        }

       // super.onActivityResult(requestCode, resultCode, data)
    }


    /**
     * 图片选择完成的处理
     *
     */
    private fun handleImageRequestResult(intent:Intent){
        val imageUri: Uri? = intent.clipData?.let {
            it.getItemAt(0).uri
        }?:intent.data

        if(imageUri == null){
            Log.e(TAG, "Invalid input image Uri.")
            return

        }

        sweetAlertDialog.show()
        //图片模糊处理
        model.setImageUri(imageUri.toString())
        model.applyBlur(3)

    }



}