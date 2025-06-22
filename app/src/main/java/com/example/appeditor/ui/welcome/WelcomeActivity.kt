package com.example.appeditor.ui.welcome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.appeditor.R
import com.example.appeditor.constant.Constant
import com.example.appeditor.ui.splash.SplashActivity
import androidx.core.content.edit

class WelcomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: WelcomePagerAdapter
    private lateinit var nextButton: Button
    private lateinit var skipButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        viewPager = findViewById(R.id.welcome_view_pager)
        nextButton = findViewById(R.id.next_button)
        skipButton = findViewById(R.id.skip_button)

        pagerAdapter = WelcomePagerAdapter(this)

        pagerAdapter.addFragment(
            WelcomeFragment.newInstance(
                R.string.lb_title_1.toString(),
                R.string.lb_desc_1.toString(),
                R.drawable.ic_launcher_foreground
            )
        )
        pagerAdapter.addFragment(
            WelcomeFragment.newInstance(
                R.string.lb_title_2.toString(),
                R.string.lb_desc_2.toString(),
                R.drawable.ic_launcher_foreground
            )
        )
        pagerAdapter.addFragment(
            WelcomeFragment.newInstance(
                R.string.lb_title_3.toString(),
                R.string.lb_desc_3.toString(),
                R.drawable.ic_launcher_foreground
            )
        )

        viewPager.adapter = pagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == pagerAdapter.itemCount - 1) {
                    nextButton.text = R.string.lb_start.toString()
                    skipButton.visibility = View.GONE
                } else {
                    nextButton.text = R.string.lb_next.toString()
                    skipButton.visibility = View.VISIBLE
                }
            }
        })

        nextButton.setOnClickListener {
            if (viewPager.currentItem < pagerAdapter.itemCount - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
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