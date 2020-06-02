package com.knight.mvvm_project_01.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.knight.mvvm_project_01.databinding.FavouriteFragmentBinding
import com.knight.mvvm_project_01.ui.adapter.FavouriteAdapter
import com.knight.mvvm_project_01.viewmodel.CustomViewModelProvider
import com.knight.mvvm_project_01.viewmodel.FavouriteModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/20 19:42
 * @descript:
 */
class FavouriteFragment :Fragment() {


    private val viewModel: FavouriteModel by viewModels {
        CustomViewModelProvider.providerFavouriteModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?{
        val binding:FavouriteFragmentBinding = FavouriteFragmentBinding.inflate(inflater,container,false)
        context ?: return binding.root
        val adapter = FavouriteAdapter(context!!)
        binding.recycler.adapter = adapter
        onSubscribeUi(adapter,binding)
        return binding.root
    }





    private fun onSubscribeUi(adapter: FavouriteAdapter,binding:FavouriteFragmentBinding){
        binding.empty.bind(arrayOf(binding.recycler))
        binding.empty.triggerLoading()

        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if(it != null && it.isNotEmpty()){
                adapter.submitList(it)
            }
            binding.empty.triggerOkOrEmpty(adapter.itemCount > 0)
        })


    }
}