package com.yeonkims.booksearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yeonkims.booksearch.databinding.ListItemKeywordBinding
import com.yeonkims.booksearch.view.BookListFragment
import com.yeonkims.booksearch.view.KeywordListViewModel

class KeywordListAdapter(private val viewModel: KeywordListViewModel) : ListAdapter<String, KeywordListAdapter.ViewHolder>(KeywordDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: ListItemKeywordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.keyword = item

            binding.searchItem.setOnClickListener {
                val navController = binding.root.findNavController()
                navController.previousBackStackEntry?.savedStateHandle?.set(BookListFragment.SAVED_KEYWORD_KEY, item)
                navController.popBackStack()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemKeywordBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class KeywordDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
