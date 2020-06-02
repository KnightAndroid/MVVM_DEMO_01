package com.knight.mvvm_project_01.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.databinding.FavouriteRecyclerItemBinding
import com.knight.mvvm_project_01.db.data.Shoe
import com.knight.mvvm_project_01.ui.activity.DetailActivity


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/6/2 19:01
 * @descript:收藏记录的适配器
 */
class FavouriteAdapter constructor(val context: Context) : ListAdapter<Shoe, FavouriteAdapter.FavouriteViewHolder>(ShoeDiffCallback()) {



    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
       val shoe = getItem(position)
       holder.apply {
           bind(onCreateListener(shoe.id),shoe)
           itemView.tag = shoe
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
       return FavouriteViewHolder(
           FavouriteRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       )
    }


    private fun onCreateListener(id:Long): View.OnClickListener{
        return View.OnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID,id)
            context.startActivity(intent)
        }

    }


    class FavouriteViewHolder(private val binding: FavouriteRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
       fun bind(listener: View.OnClickListener,item : Shoe){
           binding.apply {
               this.listener = listener
               this.shoe = item
               this.price = item.price.toString()
               executePendingBindings()
           }
       }


    }
}