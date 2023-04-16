package com.yeonkims.booksearch.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
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

        setHasOptionsMenu(true)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            findNavController().navigate(R.id.action_bookListFragment_to_keywordListFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}