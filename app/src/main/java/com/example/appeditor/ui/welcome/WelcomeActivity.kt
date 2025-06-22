package com.example.appeditor.ui.welcome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.appeditor.R
import com.example.appeditor.constant.Constant
import com.example.appeditor.ui.splash.SplashActivity
import androidx.core.content.edit
import com.example.appeditor.databinding.ActivityWelcomeBinding
import kotlin.getValue

class WelcomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityWelcomeBinding.inflate(layoutInflater) }

    private lateinit var pagerAdapter: WelcomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        pagerAdapter = WelcomePagerAdapter(this)
        initUI()
    }

    private fun initUI() {
        binding.apply {
            pagerAdapter.addFragment(
                WelcomeFragment.newInstance(
                    getString(R.string.lb_title_1),
                    getString(R.string.lb_desc_1),
                    R.drawable.ic_launcher_foreground
                )
            )
            pagerAdapter.addFragment(
                WelcomeFragment.newInstance(
                    getString(R.string.lb_title_2),
                    getString(R.string.lb_desc_2),
                    R.drawable.ic_launcher_foreground
                )
            )
            pagerAdapter.addFragment(
                WelcomeFragment.newInstance(
                    getString(R.string.lb_title_2),
                    getString(R.string.lb_desc_2),
                    R.drawable.ic_launcher_foreground
                )
            )

            welcomeViewPager.adapter = pagerAdapter

            welcomeViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == pagerAdapter.itemCount - 1) {
                        nextButton.text = getString(R.string.lb_start)
                        skipButton.visibility = View.GONE
                    } else {
                        nextButton.text = getString(R.string.lb_next)
                        skipButton.visibility = View.VISIBLE
                    }
                }
            })

            nextButton.setOnClickListener {
                if (welcomeViewPager.currentItem < pagerAdapter.itemCount - 1) {
                    welcomeViewPager.currentItem = welcomeViewPager.currentItem + 1
                } else {
                    markWelcome()
                    startMainActivity()
                }
            }

            skipButton.setOnClickListener {
                markWelcome()
                startMainActivity()
            }
        }
    }

    private fun markWelcome() {
        val sharedPref = getSharedPreferences(Constant.PREFS_NAME, Context.MODE_PRIVATE)
        sharedPref.edit {
            putBoolean(Constant.KEY_SEEN_WELCOME, true)
            apply()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this@WelcomeActivity, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }
}