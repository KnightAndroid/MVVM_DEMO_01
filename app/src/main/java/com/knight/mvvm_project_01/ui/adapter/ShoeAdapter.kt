package com.knight.mvvm_project_01.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.databinding.ShoeRecyclerItemBinding
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.ui.activity.DetailActivity


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/29 14:55
 * @descript:鞋子的适配器 配合Data binding使用
 */
class ShoeAdapter constructor(val context: Context) : PagedListAdapter<Shoe,ShoeAdapter.ViewHolder>(ShoeDiffCallback()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShoeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val shoe = getItem(position)
       holder.apply {
           bind(onCreateListener(shoe!!.id),shoe)
           itemView.tag = shoe
       }
    }

    /**
     *
     * Holder的点击事件
     *
     */
    private fun onCreateListener(id:Long): View.OnClickListener{
        return View.OnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID,id)
            context.startActivity(intent)
        }
    }


    //ViewHolder
    class ViewHolder(private val binding:ShoeRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(listener:View.OnClickListener,item:Shoe){
            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }

        }
    }
}