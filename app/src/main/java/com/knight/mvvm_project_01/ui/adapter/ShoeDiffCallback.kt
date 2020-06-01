package com.knight.mvvm_project_01.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.knight.mvvm_project_01.db.data.Shoe


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/6/1 13:42
 * @descript:局部更新差异
 */
class ShoeDiffCallback:DiffUtil.ItemCallback<Shoe>() {
    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem == newItem
    }


}