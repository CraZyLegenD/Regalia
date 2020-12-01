package com.commit451.regalia.sample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.commit451.regalia.moshi.RealmListJsonAdapterFactory
import com.commit451.regalia.sample.adapter.TestModel
import com.crazylegend.retrofit.RetrofitClient
import com.crazylegend.retrofit.coroutines.makeApiCallLiveData
import com.crazylegend.retrofit.retrofitResult.RetrofitResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


/**
 * Created by hristijan on 8/26/19 to long live and prosper !
 */

/**
 * Template created by Hristijan to live long and prosper.
 */

class TestAVM(application: Application) : AndroidViewModel(application) {

    private val postsData: MediatorLiveData<RetrofitResult<List<TestModel>>> = MediatorLiveData()
    val posts: LiveData<RetrofitResult<List<TestModel>>> = postsData


    fun getposts() {
        makeApiCallLiveData(postsData) { retrofit.getPosts() }
    }

    init {
        getposts()
    }

    private val moshiConverterFactory by lazy {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(RealmListJsonAdapterFactory())
                .build()
        MoshiConverterFactory.create(moshi)
    }

    private val retrofit by lazy {
        RetrofitClient.customInstance(context = application, baseUrl = TestApi.API, enableDebuggingInterceptor = true){
            addConverterFactory(moshiConverterFactory)
            this
        }.create<TestApi>()
    }


}



