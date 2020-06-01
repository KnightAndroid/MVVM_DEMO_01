package com.knight.mvvm_project_01.common

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/26 20:46
 * @descript:
 */

fun <T> DataSource.Factory<Int,T>.createPagerList(pageSize:Int,defaultSize:Int): LiveData<PagedList<T>> {
    return LivePagedListBuilder<Int,T>(this,PagedList.Config.Builder()
        .setPageSize(2)
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(2)
        .build()
    ).build()
}