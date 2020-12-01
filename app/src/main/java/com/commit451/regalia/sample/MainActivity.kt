package com.commit451.regalia.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.commit451.regalia.sample.adapter.TestAdapter
import com.commit451.regalia.sample.databinding.ActivityMainBinding
import com.crazylegend.kotlinextensions.activity.launchActivity
import com.crazylegend.kotlinextensions.delegates.activityVM
import com.crazylegend.kotlinextensions.exhaustive
import com.crazylegend.kotlinextensions.log.debug
import com.crazylegend.recyclerview.RecyclerSwipeItemHandler
import com.crazylegend.recyclerview.addSwipe
import com.crazylegend.recyclerview.clickListeners.forItemClickListener
import com.crazylegend.recyclerview.initRecyclerViewAdapter
import com.crazylegend.retrofit.retrofitResult.RetrofitResult
import com.crazylegend.viewbinding.viewBinder


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val testAVM by activityVM<TestAVM>()
    private val adapter by lazy {
        TestAdapter()
    }
    private val binding by viewBinder(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.recycler.initRecyclerViewAdapter(adapter)



        binding.recycler.addSwipe(this) {
            swipeDirection = RecyclerSwipeItemHandler.SwipeDirs.BOTH
            drawableLeft = android.R.drawable.ic_delete
            drawLeftBackground = true
            leftBackgroundColor = R.color.colorPrimary
            drawableRight = android.R.drawable.ic_input_get
        }


        testAVM.posts.observe(this, {
            it?.apply {
                when (it) {
                    is RetrofitResult.Success -> {
                        adapter.submitList(it.value)

                        adapter.forItemClickListener = forItemClickListener { _, item, _ ->
                            launchActivity<TestParcelActivity> {
                                putExtra("test", item)
                                putParcelableArrayListExtra("test2", ArrayList(it.value))
                            }
                        }

                        /*val wrappedList = it.value.toMutableList()
                        recycler.addDrag(adapter, wrappedList)*/

                    }
                    RetrofitResult.Loading -> {
                        debug(it.toString())
                    }
                    RetrofitResult.EmptyData -> {
                        debug(it.toString())
                    }
                    is RetrofitResult.Error -> {
                        debug(it.toString())
                    }
                    is RetrofitResult.ApiError -> {
                        debug(it.toString())
                    }

                }.exhaustive
            }
        })
    }

}
