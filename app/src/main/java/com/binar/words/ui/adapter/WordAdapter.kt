package com.binar.words.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.words.`interface`.OnItemClickCallback
import com.binar.words.databinding.LetterListItemBinding

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val wordList = ArrayList<String>()

    fun submitData(list: List<String>) {
        wordList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LetterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]

        with(holder) {
            binding.btnLetter.text = word

            binding.btnLetter.setOnClickListener {
                onItemClickCallback.onItemClicked(word)
            }
        }
    }

    override fun getItemCount(): Int = wordList.size

    class ViewHolder(val binding: LetterListItemBinding) : RecyclerView.ViewHolder(binding.root)
}