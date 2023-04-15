package com.yeonkims.booksearch.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yeonkims.booksearch.R
import com.yeonkims.booksearch.adapter.BookListAdapter
import com.yeonkims.booksearch.databinding.FragmentBookListBinding

class BookListFragment : Fragment() {

    lateinit var binding: FragmentBookListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_list, container, false)

        val viewModel = BookListViewModel()

        val adapter = BookListAdapter(viewModel)
        binding.bookList.adapter = adapter

        viewModel.bookList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}