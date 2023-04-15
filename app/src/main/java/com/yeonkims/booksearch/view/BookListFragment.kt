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
import com.yeonkims.booksearch.model.fakeBookData

class BookListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_list, container, false)

        val viewModel = BookListViewModel()

        val adapter = BookListAdapter()
        binding.bookList.adapter = adapter

        viewModel.bookList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.lifecycleOwner = this

        return binding.root
    }
}