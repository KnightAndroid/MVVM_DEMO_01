package com.knight.mvvm_project_01.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import cn.onestravel.one.navigation.androidx.OneBottomNavigationBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.knight.mvvm_project_01.R
import com.knight.mvvm_project_01.ui.fragment.main.FavouriteFragment
import com.knight.mvvm_project_01.ui.fragment.main.MeFragment
import com.knight.mvvm_project_01.ui.fragment.main.ShoeFragment

class MainActivity : AppCompatActivity() {



//    lateinit var bottomNavigationView:BottomNavigationView
    lateinit var mToolbar:Toolbar
    lateinit var mCamera:ImageView
    lateinit var oneBottomNavigationBar:OneBottomNavigationBar
    lateinit var my_nav_host_fragment: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//      val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        initWidget()
        initBottomWidget()
//      val navController = host.navController
//      initBottomNavgationView(bottomNavigationView,navController)
    }





    private fun initBottomNavgationView(bottomNavigationView: BottomNavigationView,navController: NavController){
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{
            controller, destination, arguments ->
            when(destination.id){
                R.id.meFragment -> mCamera.visibility = View.VISIBLE
                else -> mCamera.visibility = View.GONE

            }
        }
    }

    private fun initWidget(){
       // bottomNavigationView = findViewById(R.id.bnv_view)
        mToolbar = findViewById(R.id.toolbar)
        mCamera = findViewById(R.id.iv_camera)
        oneBottomNavigationBar = findViewById(R.id.bnv_view)
        my_nav_host_fragment = findViewById(R.id.my_nav_host_fragment)
        mCamera.setOnClickListener{

        }
    }


    private fun initBottomWidget(){
        oneBottomNavigationBar.setMenu(R.menu.menu_main)
        oneBottomNavigationBar.setFragmentManager(supportFragmentManager,my_nav_host_fragment)
        oneBottomNavigationBar.addFragment(R.id.infoFragment,ShoeFragment())
        oneBottomNavigationBar.addFragment(R.id.marketFragment,FavouriteFragment())
        oneBottomNavigationBar.addFragment(R.id.meFragment,MeFragment())
        //未读消失
        oneBottomNavigationBar.setMsgCount(0,5)
    }
}
