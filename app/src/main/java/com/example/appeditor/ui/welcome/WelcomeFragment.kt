package com.example.appeditor.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appeditor.R
import com.example.appeditor.constant.Constant

class WelcomeFragment : Fragment() {
    private var title: String? = null
    private var description: String? = null
    private var imageResource: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            description = it.getString(ARG_DESCRIPTION)
            imageResource = it.getInt(ARG_IMAGE_RESOURCE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome_page, container, false)
        val welcomeImage = view.findViewById<ImageView>(R.id.welcome_image)
        val welcomeTitle = view.findViewById<TextView>(R.id.welcome_title)
        val welcomeDescription = view.findViewById<TextView>(R.id.welcome_description)

        welcomeTitle.text = title
        welcomeDescription.text = description
        if (imageResource != 0) {
            welcomeImage.setImageResource(imageResource)
        }

        return view
    }

    companion object {
        private const val ARG_TITLE = Constant.ARG_TITLE
        private const val ARG_DESCRIPTION = Constant.ARG_DESCRIPTION
        private const val ARG_IMAGE_RESOURCE = Constant.ARG_IMAGE_RESOURCE


        @JvmStatic
        fun newInstance(title: String, description: String, imageResId: Int): WelcomeFragment {
            return WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESCRIPTION, description)
                    putInt(ARG_IMAGE_RESOURCE, imageResId)
                }
            }
        }
    }


}