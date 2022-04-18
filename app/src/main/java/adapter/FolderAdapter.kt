package com.example.notesapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.NotesActivity
import com.example.notesapp.PARENT_SET_ID_TAG
import com.example.notesapp.runOnIO
import entities.NotesFolders
import entities.FolderDao
import com.example.notesapp.runOnIO
import com.example.notesapp.databinding.ItemNoteFolderBinding

//ngl i took all this from the flashcard project

class FolderAdapter (private val dao: FolderDao) :
        RecyclerView.Adapter<FolderAdapter.ViewHolder>() {

        private lateinit var dataSet: MutableList<NotesFolders>

        init {
            runOnIO { dataSet =  dao.getAll().toMutableList()}
        }

    class ViewHolder(bind: ItemNoteFolderBinding) : RecyclerView.ViewHolder(bind.root) {
        val binding: ItemNoteFolderBinding = bind
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteFolderBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.minimumHeight = parent.height / 5
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, NotesActivity::class.java)
            intent.putExtra(PARENT_SET_ID_TAG, dataSet[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun addItem(it: NotesFolders) {
        runOnIO {
            dataSet.add(it.copy(id = dao.insert(it)))
        }
        notifyItemInserted(dataSet.lastIndex)
    }

}