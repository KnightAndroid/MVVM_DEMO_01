package com.knight.mvvm_project_01.ui.activity

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.databinding.ActivityDetailBinding
import com.knight.mvvm_project_01.utils.AppPrefsUtils
import com.knight.mvvm_project_01.viewmodel.CustomViewModelProvider
import com.knight.mvvm_project_01.viewmodel.DetailModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:09
 * @descript:
 */
class DetailActivity : AppCompatActivity(){


    private val detailModel:DetailModel by viewModels<DetailModel>{
       CustomViewModelProvider.providerDetailModel(
           this,intent.getLongExtra(BaseConstant.DETAIL_SHOE_ID,1L),AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
       )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        onSubscribeUi(binding)
        initListener(binding)

    }





    private fun initListener(binding:ActivityDetailBinding) {
        binding.ivBack.setOnClickListener {
        onBackPressed()
       }

        //设置点击动画
        binding.fbFavourite.setOnClickListener {
            binding.fbFavourite.animate()
                .rotation(360.0f)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .setListener(object : Animator.AnimatorListener{
                    override fun onAnimationEnd(animation: Animator?) {
                        detailModel.favourite()
                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationStart(animation: Animator?) {

                    }

                    override fun onAnimationRepeat(animation: Animator?) {

                    }


                }).setDuration(200)
                .start()
        }
    }


    private fun onSubscribeUi(binding:ActivityDetailBinding){
        detailModel.shoe.observe(this, Observer {
            binding.shoe = it
            binding.price = it.price.toString()
        })

        detailModel.favouriteShoe.observe(this, Observer {
            binding.v = if (it == null) View.VISIBLE else View.GONE
        })

    }
}