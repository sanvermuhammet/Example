package com.sanver.example.ui.main.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.sanver.example.R
import com.sanver.example.core.BaseAdapter
import com.sanver.example.databinding.RvMovieItemBinding
import com.sanver.example.service.response.MovieResponse
import com.sanver.example.ui.main.MainActivityViewModel


class MovieAdapter(private val callback: (MovieResponse, Int) -> Unit) :
    BaseAdapter<MovieResponse?>(object : DiffUtil.ItemCallback<MovieResponse?>() {
        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<RvMovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rv_movie_item,
            parent,
            false
        )
        val viewModel =
            MainActivityViewModel((parent.context as Activity).application)


        mBinding.viewModel = viewModel


        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {

        getItem(position)?.let { (binding as RvMovieItemBinding).viewModel?.setModel(it, position) }
        binding.executePendingBindings()
    }
}