package com.lzp.daily.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.lzp.daily.R
import com.lzp.daily.databinding.ActivityMainBinding
import com.lzp.daily.viewmodel.home.HomeViewmodel

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: HomeAdapter
    private lateinit var mViewModel: HomeViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = HomeViewmodel()
        binding.viewmodel = mViewModel
        setupRecyclerView(binding)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.start()
    }

    private fun setupRecyclerView(binding: ActivityMainBinding) {
        mAdapter = HomeAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.adapter = mAdapter
    }
}