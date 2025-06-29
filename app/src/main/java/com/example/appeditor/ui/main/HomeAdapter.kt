package com.example.appeditor.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appeditor.R
import com.example.appeditor.models.Feature

class HomeAdapter(
    private val features: List<Feature>,
    private val onItemClick: (Feature) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val featureName: TextView
        val featureIcon: ImageView

        init {
            featureName = itemView.findViewById(R.id.tv_feature_name)
            featureIcon = itemView.findViewById(R.id.btn_edit_photo)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feature, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feature = features[position]
        holder.featureName.text = feature.featureName
        holder.featureIcon.setImageResource(feature.featureIcon)

        holder.itemView.setOnClickListener {
            onItemClick(features[position])
        }
    }

    override fun getItemCount(): Int {
        return features.size
    }
}