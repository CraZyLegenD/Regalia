package com.commit451.regalia.sample.adapter

import com.commit451.regalia.sample.databinding.RecyclerViewItemBinding
import com.crazylegend.recyclerview.AbstractViewBindingAdapter


/**
 * Created by hristijan on 10/25/19 to long live and prosper !
 */
//extract the classes to separate files


/**
 * Template created by Hristijan to live long and prosper.
 */

class TestAdapter : AbstractViewBindingAdapter<TestModel, TestViewHolder, RecyclerViewItemBinding>(::TestViewHolder, RecyclerViewItemBinding::inflate) {

    override fun bindItems(item: TestModel, holder: TestViewHolder, position: Int, itemCount: Int) {
        holder.bind(item)
    }
}


