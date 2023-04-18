package com.yeonkims.booksearch.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yeonkims.booksearch.R
import com.yeonkims.booksearch.adapter.BookListAdapter
import com.yeonkims.booksearch.databinding.FragmentBookListBinding

class BookListFragment : Fragment() {

    private val scrollBoundary = 1000

    lateinit var binding: FragmentBookListBinding

    val viewModel: BookListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_list, container, false)


        val adapter = BookListAdapter(viewModel)
        binding.bookList.adapter = adapter
        binding.bookList.layoutManager = LinearLayoutManager(requireContext())
        binding.bookList.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offset = recyclerView.computeVerticalScrollOffset()
                val extent = recyclerView.computeVerticalScrollExtent()
                val range = recyclerView.computeVerticalScrollRange()

                val scrollAmount = range - extent
                val remainingScroll = scrollAmount - offset

                if(remainingScroll < scrollBoundary) {
                    viewModel.loadNextPage()
                }
            }
        })

        viewModel.bookList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
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