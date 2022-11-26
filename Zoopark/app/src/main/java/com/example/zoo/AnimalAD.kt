package com.plcoding.retrofitcrashcourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.retrofitcrashcourse.databinding.ItemTodoBinding

class Animal : RecyclerView.Adapter<com.plcoding.retrofitcrashcourse.TodoAdapter.Animal.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var animals: List<Animal>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = animals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = animals[position]
            name.text = todo.name
            latinName.text = todo.latin_name
            animalType.text = todo.animal_type

        }
    }
}