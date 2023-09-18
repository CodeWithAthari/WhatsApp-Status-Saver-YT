package com.devatrii.statussaver.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devatrii.statussaver.databinding.ItemSettingsBinding
import com.devatrii.statussaver.models.SettingsModel

class SettingsAdapter(var list: ArrayList<SettingsModel>, var context: Context) :
    RecyclerView.Adapter<SettingsAdapter.viewHolder>() {

    class viewHolder(var binding: ItemSettingsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SettingsModel, context: Context) {
            binding.apply {
                settingsTitle.text = model.title
                settingsDesc.text = model.desc
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemSettingsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(model = list[position], context = context)
    }
}