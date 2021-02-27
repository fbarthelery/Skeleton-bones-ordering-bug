package com.geekorum.bugs.skeletonbonesordering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekorum.bugs.skeletonbonesordering.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = myViewModel

            // because of bug https://github.com/EudyContreras/Skeleton-Bones/issues/16
            // we instantiate the skeleton in code
//            SkeletonDrawable.create(root as ViewGroup, false)
        }
    }
}

class MyViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>().apply {
        value = true
    }

    init {
        viewModelScope.launch {
            delay(5000)
            loading.value = false
        }
    }
}
