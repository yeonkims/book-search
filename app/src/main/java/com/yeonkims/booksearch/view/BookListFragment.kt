package com.yeonkims.booksearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yeonkims.booksearch.R
import com.yeonkims.booksearch.adapter.BookListAdapter
import com.yeonkims.booksearch.database.AppDatabase
import com.yeonkims.booksearch.databinding.FragmentBookListBinding


class BookListFragment : Fragment() {


    private lateinit var binding: FragmentBookListBinding

    lateinit var viewModel: BookListViewModel
    private lateinit var adapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = BookListViewModel(AppDatabase.getDb(requireContext()).keywordDao())
        adapter = BookListAdapter(viewModel)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(SAVED_KEYWORD_KEY)?.observe(viewLifecycleOwner) { savedSearchWord ->
            viewModel.searchNewWord(savedSearchWord)
        }

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_list, container, false)

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

                if(remainingScroll < SCROLL_BOUNDARY) {
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

    companion object {
        const val SAVED_KEYWORD_KEY = "SAVED_KEYWORD_KEY"
        private const val SCROLL_BOUNDARY = 1000
    }
}