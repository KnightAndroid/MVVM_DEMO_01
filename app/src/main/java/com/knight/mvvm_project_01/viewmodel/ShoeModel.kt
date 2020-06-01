package com.knight.mvvm_project_01.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.knight.mvvm_project_01.common.createPagerList
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.db.datasource.CustomPageDataSourceFactory
import com.knight.mvvm_project_01.db.repository.ShoeRepository


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/20 15:59
 * @descript:鞋子模型
 */
class ShoeModel constructor(shoeRepository:ShoeRepository): ViewModel() {

    //品牌的观察对象 默认观察所有的品牌
    private val brand = MutableLiveData<String>().apply {
        value = ALL
    }

    //鞋子集合的观察类
    //Transformations.switchMap Function: 用一个LiveData<X>的value改变来触发另外一个LiveData<Y>的获取
    val shoes: LiveData<PagedList<Shoe>> = brand.switchMap {
       // Room数据库查询，只要知道返回的是LiveData<List<Shoe>>即可
       if(it == ALL){
         // LivePagedListBuilder<Int,Shoe>(shoeRepository.getAllShoes(),PagedList.Config.Builder())
         LivePagedListBuilder<Int,Shoe>(
             CustomPageDataSourceFactory(shoeRepository),PagedList.Config.Builder()
                 .setPageSize(10)//分页加载的数量
                 .setEnablePlaceholders(false)//当item为null是否使用PlaceHolder展示
                 .setInitialLoadSizeHint(10) //预加载的数量
                 .build()
         ) .build()

       } else {
           val array: Array<String> =
               when(it){
                   NIKE -> arrayOf("Nike","Air Jordan")
                   ADIDAS -> arrayOf("Adidas")
                   else -> arrayOf(
                       "Converse","UA","ANTA"
                   )
               }

           shoeRepository.getShoesByBrand(array).createPagerList(6, 6)
       }
    }



    fun setBrand(brand:String){
        this.brand.value = brand
        this.brand.map{

        }

    }




    companion object {
        const val  ALL = "所有"
        const val NIKE= "Nike"
        const val ADIDAS = "Adidas"
        const val OTHER = "other"
    }
}