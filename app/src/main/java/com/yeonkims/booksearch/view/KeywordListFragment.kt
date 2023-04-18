package com.yeonkims.booksearch.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeonkims.booksearch.R
import com.yeonkims.booksearch.adapter.KeywordListAdapter
import com.yeonkims.booksearch.database.AppDatabase
import com.yeonkims.booksearch.databinding.FragmentKeywordListBinding

class KeywordListFragment : Fragment() {

    lateinit var binding: FragmentKeywordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_keyword_list, container, false)

        val viewModel = KeywordListViewModel(AppDatabase.getDb(requireContext()).keywordDao())

        val adapter= KeywordListAdapter(viewModel)
        binding.keywordList.adapter = adapter
        binding.keywordList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.keywords.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}