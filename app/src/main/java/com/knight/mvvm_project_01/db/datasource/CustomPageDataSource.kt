package com.knight.mvvm_project_01.db.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.db.repository.ShoeRepository


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/26 20:15
 * @descript:自定义PageKeyedDataSource
 */
class CustomPageDataSource(private val shoeRepository: ShoeRepository) : PageKeyedDataSource<Int, Shoe>(){


    //第一次加载的时候调用
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Shoe>
    ) {
         val startIndex = 0L
         val endIndex:Long = 0L + params.requestedLoadSize
         val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes,null,2)
    }

    //每次分页加载的时候调用
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        Log.e(TAG, "startPage:${params.key},size:${params.requestedLoadSize}")
        val startPage = params.key
        val startIndex = ((startPage - 1) * BaseConstant.SINGLE_OAGE_SIZE).toLong() + 1
        val endIndex = startIndex + params.requestedLoadSize - 1
        val shoes = shoeRepository.getPageShoes(startIndex,endIndex)

        callback.onResult(shoes,params.key + 1)
    }

    //准备加载之前
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        Log.e(TAG, "endPage:${params.key},size:${params.requestedLoadSize}")
        val endPage = params.key
        val endIndex = ((endPage - 1) * BaseConstant.SINGLE_OAGE_SIZE).toLong() + 1
        var startIndex = endIndex - params.requestedLoadSize
        startIndex = if (startIndex < 0) 0L else startIndex
        val shoes = shoeRepository.getPageShoes(startIndex,endIndex)

        callback.onResult(shoes,params.key + 1)
    }


    private val TAG:String by lazy{
        this::class.java.simpleName
    }




}