package com.example.appeditor.ui.bitmap

import android.R.attr.contentDescription
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.View
import com.example.appeditor.R

@SuppressLint("ViewConstructor")
class CustomDrawableView(
    context: Context,
    private val backgroundBitMap: Bitmap?,
) : View(context) {
    private val drawable: ShapeDrawable = run {
        val x = 10
        val y = 10
        val width = 300
        val height = 300
        contentDescription = context.resources.getString(R.string.lb_desc_1)

        ShapeDrawable(OvalShape()).apply {
            // If the color isn't set, the shape uses black as the default.
            paint.color = 0xFFFF0000.toInt()
            // If the bounds aren't set, the shape can't be drawn.
            setBounds(x, y, x + width, y + height)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        backgroundBitMap?.let {
            canvas.drawBitmap(it, 0f, 0f, null)
        }

        drawable.draw(canvas)
    }


}