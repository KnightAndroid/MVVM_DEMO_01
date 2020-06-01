package com.knight.mvvm_project_01.db.datasource

import androidx.paging.DataSource
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.db.repository.ShoeRepository


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/26 20:13
 * @descript:构建CustomPageDataSource的工厂
 */
class CustomPageDataSourceFactory(private val shoeRepository:ShoeRepository): DataSource.Factory<Int, Shoe>() {
    override fun create(): DataSource<Int, Shoe> {
        return CustomPageDataSource(shoeRepository)
    }


}