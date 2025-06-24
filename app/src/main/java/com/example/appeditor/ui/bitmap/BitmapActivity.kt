package com.example.appeditor.ui.bitmap

import android.annotation.SuppressLint
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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.widget.Toast

class BitmapActivity : AppCompatActivity() {
    private val binding by lazy { ActivityBitmapBinding.inflate(layoutInflater) }
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var imageView: ImageView

    private var transitionDrawable: TransitionDrawable? = null
    private var isExpanded = false // State variable

    private var customDrawableView: CustomDrawableView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
        setupCustomDrawableView()
//        initUI()
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

    @SuppressLint("UseKtx")
    private fun setupCustomDrawableView() {
        // --- CÁCH KHẮC PHỤC KHI LÀ VECTOR DRAWABLE ---
        val drawable: Drawable? =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null)

        val originalBitmap: Bitmap? = if (drawable != null) {
            // Kiểm tra kiểu Drawable để xử lý phù hợp
            when (drawable) {
                is BitmapDrawable -> { // Nếu là BitmapDrawable (ảnh PNG/JPG thông thường)
                    Log.d("BitmapActivity", "BitmapDrawable")
                    drawable.bitmap
                }

                is VectorDrawable -> { // Nếu là VectorDrawable
                    val bitmap = Bitmap.createBitmap(
                        drawable.intrinsicWidth,
                        drawable.intrinsicHeight,
                        Bitmap.Config.ARGB_8888
                    )
                    Log.d("BitmapActivity", "VectorDrawable")
                    val canvas = Canvas(bitmap)
                    drawable.setBounds(0, 0, canvas.width, canvas.height)
                    drawable.draw(canvas)
                    bitmap
                }

                else -> { // Các loại Drawable khác mà ta không xử lý được thành Bitmap
                    Log.w(
                        "BitmapActivity",
                        "Unsupported Drawable type: ${drawable::class.java.simpleName}"
                    )
                    null
                }
            }
        } else {
            null // drawable itself was null
        }

        if (originalBitmap == null) {
            Log.e(
                "BitmapActivity",
                "Failed to create bitmap from R.drawable.ic_launcher_foreground."
            )
            Toast.makeText(this, "Failed to load image. Please check resource.", Toast.LENGTH_LONG)
                .show()
            return
        }

        Log.d(
            "BitmapActivity",
            "Bitmap loaded successfully. Width: ${originalBitmap.width}, Height: ${originalBitmap.height}"
        )
        val customView = CustomDrawableView(this, originalBitmap)
        setContentView(customView)
    }

}

