package com.commit451.regalia.sample.adapter

import androidx.recyclerview.widget.RecyclerView
import com.commit451.regalia.sample.R
import com.commit451.regalia.sample.databinding.RecyclerViewItemBinding

class TestViewHolder(binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val card = binding.testCard

    init {
        with(card) {
            setContentPadding(10, 10, 10, 10)
        }
    }

    fun bind(item: TestModel) {
        card.binding.title.text = item.title
        card.binding.content.text = item.body
        card.binding.image.setImageResource(R.drawable.pin_code_highlight_state)
    }

}