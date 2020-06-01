package com.knight.mvvm_project_01.ui.fragment .login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.common.BaseConstant
import com.knight.mvvm_project_01.databinding.RegisterFragmentBinding
import com.knight.mvvm_project_01.viewmodel.CustomViewModelProvider
import com.knight.mvvm_project_01.viewmodel.RegisterModel


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/11 14:13
 * @descript:
 */
class RegisterFragment : Fragment() {
    lateinit var et_email:EditText


    private val registerModel: RegisterModel by viewModels{
        CustomViewModelProvider.providerRegisterModel(requireContext())
    }
    private var isEnable:Boolean = false



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:RegisterFragmentBinding
                = DataBindingUtil.inflate(inflater,R.layout.register_fragment,container,false)
        initData(binding)
        onSubscribeUi(binding)
        return binding.root

    }

    private fun initData(binding:RegisterFragmentBinding){
        // SafeArgs的使用
        val safeVarargs:RegisterFragmentArgs by navArgs()
        val email = safeVarargs.email
        binding.model?.mail?.value = email
        binding.model = registerModel
        binding.isEnable = isEnable
        binding.activity = activity
    }
    
    
    private fun onSubscribeUi(binding:RegisterFragmentBinding){
        binding.btnRegister.setOnClickListener {
            var number = registerModel.register()
//            if(registerModel.number.value!!) {
//                val bundle = Bundle()
//                bundle.putString(BaseConstant.ARGS_NAME,registerModel.n.value)
//                findNavController().navigate(R.id.login,bundle,null)
//            } else {
//                Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show()
//            }

        }

        registerModel.p.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()
                    && registerModel.n.value!!.isNotEmpty()
                    && registerModel.mail.value!!.isNotEmpty()
        })

        registerModel.n.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()
                    && registerModel.p.value!!.isNotEmpty()
                    && registerModel.mail.value!!.isNotEmpty()
        })

        registerModel.mail.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()
                    && registerModel.n.value!!.isNotEmpty()
                    && registerModel.p.value!!.isNotEmpty()
        })

        registerModel.number.observe(viewLifecycleOwner, Observer {
            if(registerModel.number.value != -1L  && registerModel.number.value!! > 0) {
                val bundle = Bundle()
                bundle.putString(BaseConstant.ARGS_NAME,registerModel.n.value)
                findNavController().navigate(R.id.login,bundle,null)
            } else if(registerModel.number.value === 0L){
                Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show()
            }
        })

    }
}