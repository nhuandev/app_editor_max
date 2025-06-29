package com.example.appeditor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.appeditor.databinding.ActivityMainBinding
import com.example.appeditor.ui.main.add.AddFragment
import com.example.appeditor.ui.main.home.HomeFragment
import com.example.appeditor.ui.main.setting.SettingFragment
import com.example.appeditor.ui.main.ViewPagerAdapter
import com.example.appeditor.ui.splash.SplashRepository
import com.example.appeditor.ui.splash.SplashViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: SplashViewModel by lazy { SplashViewModel(SplashRepository(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        val fragments = listOf(
            HomeFragment(),
            AddFragment(),
            SettingFragment()
        )

        val adapterMain = ViewPagerAdapter(this, fragments)

        binding.apply {
            viewpagerMain.adapter = adapterMain
            viewpagerMain.isUserInputEnabled = false

            viewpagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                @SuppressLint("UseKtx")
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bottomNavigation.menu.getItem(position).isChecked = true

                    when (position) {
                        0 -> toolBar.visibility = View.VISIBLE
                        1 -> toolBar.visibility = View.GONE
                        2 -> toolBar.visibility = View.GONE
                    }
                }
            })

            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home -> viewpagerMain.currentItem = 0
                    R.id.nav_add -> viewpagerMain.currentItem = 1
                    R.id.nav_setting -> viewpagerMain.currentItem = 2
                }
                true
            }
        }
    }
}
