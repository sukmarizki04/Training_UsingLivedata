package com.binar.words.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.words.`interface`.OnItemClickCallback
import com.binar.words.databinding.LetterListItemBinding

class LetterAdapter : RecyclerView.Adapter<LetterAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val letterList = ArrayList<String>()

    fun submitData(list: List<String>) {
        letterList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LetterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val letter = letterList[position]

        with(holder) {
            binding.btnLetter.text = letter

            binding.btnLetter.setOnClickListener {
                onItemClickCallback.onItemClicked(binding.btnLetter.text.toString())
            }
        }

    }

    override fun getItemCount(): Int = letterList.size

    class ViewHolder (val binding: LetterListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
