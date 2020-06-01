package com.knight.mvvm_project_01.binding

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.common.listener.SimpleWatcher
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/6/1 17:13
 * @descript:适配器属性自定义
 */


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view:ImageView,imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
       Glide.with(view.context).asBitmap().load(imageUrl).placeholder(R.drawable.glide_placeholder).centerCrop().into(view)
    }
}



//加载带圆角的头像
@BindingAdapter("imageTransFromUrl")
fun bindImageTransFromUrl(view:ImageView,imageUrl: String?){
    if(!imageUrl.isNullOrEmpty()){
        Glide.with(view.context).load(imageUrl).apply(
            RequestOptions.bitmapTransform(
                RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.ALL)
            )
        ).into(view)
    }
}




//文本监听器
@BindingAdapter("addTextChangedListener")
fun addTextChangedListener(editText: EditText,simpleWatcher: SimpleWatcher){
    editText.addTextChangedListener(simpleWatcher)

}




