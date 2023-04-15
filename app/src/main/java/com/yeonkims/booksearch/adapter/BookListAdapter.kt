package com.yeonkims.booksearch.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yeonkims.booksearch.databinding.ListItemBookBinding
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.view.BookListViewModel

class BookListAdapter(val bookListViewModel: BookListViewModel)
    : ListAdapter<Book, BookListAdapter.ViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, bookListViewModel)
    }

    class ViewHolder(private val binding: ListItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, bookListViewModel: BookListViewModel) {
            binding.book = item
            binding.bookListViewModel = bookListViewModel

            binding.bookLayout.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.link)
                binding.root.context.startActivity(intent)
            }


        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBookBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.title == newItem.title
    }
}
