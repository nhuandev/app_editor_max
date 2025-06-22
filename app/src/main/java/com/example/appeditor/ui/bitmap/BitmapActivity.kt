package com.example.appeditor.ui.bitmap

import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import com.example.appeditor.R
import com.example.appeditor.databinding.ActivityBitmapBinding

class BitmapActivity : AppCompatActivity() {
    private val binding by lazy { ActivityBitmapBinding.inflate(layoutInflater) }
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var imageView: ImageView

    private var transitionDrawable: TransitionDrawable? = null
    private var isExpanded = false // State variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
//        imageView = ImageView(this).apply {
//            setImageResource(R.drawable.ic_launcher_background)
//            contentDescription = "Bitmap"
//
//            // Đặt giới hạn ImageView để phù hợp với kích thước của Drawable
//            adjustViewBounds = true
//            layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//            )
//        }
//
//        constraintLayout = ConstraintLayout(this@BitmapActivity).apply {
//            addView(imageView)
//        }
//        constraintLayout.setPadding(40)
//        setContentView(constraintLayout)

        val imageView = binding.myImageView

        imageView.setImageResource(R.drawable.ic_launcher_background)
        imageView.contentDescription = resources.getString(R.string.app_name)

        val loadedDrawable: Drawable? = ResourcesCompat.getDrawable(
            resources,
            R.drawable.expand_collapse,
            null
        )

        binding.apply {
            if (loadedDrawable is TransitionDrawable) {
                transitionDrawable = loadedDrawable
                imageView.setImageDrawable(transitionDrawable)
                imageView.contentDescription = resources.getString(R.string.app_name)
                btnBitmap.setOnClickListener {
                    if (isExpanded) {
                        transitionDrawable?.reverseTransition(1000)
                        Log.d("TAG", "initUI: $isExpanded")
                    } else {
                        transitionDrawable?.startTransition(1000)
                        Log.d("TAG", "initUI: $isExpanded")
                    }
                    isExpanded = !isExpanded
                }

            } else {
                Log.d("TAG", "initUI: else")
            }

        }

    }


}

