package com.example.appeditor.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appeditor.utils.Constant
import com.example.appeditor.databinding.FragmentWelcomePageBinding

class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomePageBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString(ARG_TITLE)
        val desc = arguments?.getString(ARG_DESCRIPTION)
        val imageRes = arguments?.getInt(ARG_IMAGE_RESOURCE)

        binding.welcomeTitle.text = title
        binding.welcomeDescription.text = desc
        imageRes?.let { binding.welcomeImage.setImageResource(it) }
    }
}
