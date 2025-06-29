package com.example.appeditor.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appeditor.R
import com.example.appeditor.databinding.FragmentHomeBinding
import com.example.appeditor.models.Feature
import com.example.appeditor.ui.main.HomeAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterHome: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val demoList = listOf(
            Feature("Edit Photo", R.drawable.ic_photo, 1),
            Feature("Make a Collage", R.drawable.ic_collage, 2),
            Feature("Make a Collage", R.drawable.ic_collage, 3),
            Feature("Make a Collage", R.drawable.ic_collage, 4),
        )

        adapterHome = HomeAdapter(
            demoList,
            onItemClick = { feature ->
                when (feature.featureType) {
                    1 -> {
                        Toast.makeText(this.context, "Edit Photo", Toast.LENGTH_SHORT).show()
                    }

                    2 -> {
                        Toast.makeText(this.context, "Make a Collage", Toast.LENGTH_SHORT).show()
                    }

                    3 -> {
                        Toast.makeText(this.context, "Make a Collage", Toast.LENGTH_SHORT).show()
                    }

                    4 -> {
                        Toast.makeText(this.context, "Make a Collage", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(this.context, "Unknown", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        binding.recFeature.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = adapterHome
        }
    }
}
