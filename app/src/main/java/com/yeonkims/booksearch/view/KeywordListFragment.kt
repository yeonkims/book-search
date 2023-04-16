package com.yeonkims.booksearch.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.yeonkims.booksearch.R
import com.yeonkims.booksearch.databinding.FragmentKeywordListBinding

class KeywordListFragment : Fragment() {

    lateinit var binding: FragmentKeywordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_keyword_list, container, false)

        val viewModel = KeywordListViewModel()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}