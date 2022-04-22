package com.example.notesapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.NotesActivity
import com.example.notesapp.PARENT_SET_ID_TAG
import com.example.notesapp.runOnIO
import entities.NotesFolders
import entities.FolderDao
import com.example.notesapp.runOnIO
import com.example.notesapp.databinding.ItemNoteFolderBinding

//ngl almost all of this is from the flashcards

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
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.binding.NotesFolderTitle.text = item.title
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, NotesActivity::class.java)
            intent.putExtra(PARENT_SET_ID_TAG, dataSet[position].id)
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener{
            showEditDialog(it.context, position)
            true
        }
    }

    private fun showEditDialog(context: Context, position: Int) {
        val customTitle = EditText(context)
        val folder = dataSet[position]
        customTitle.setText(folder.title)
        customTitle.textSize = 10 * context.resources.displayMetrics.scaledDensity
        AlertDialog.Builder(context)
            .setCustomTitle(customTitle)
            .setPositiveButton("Done") { _, _ ->
                folder.title = customTitle.text.toString()
                runOnIO { dao.update(folder) }
                notifyItemChanged(position)
            }
            .setNegativeButton("Delete") { _,_ ->
                runOnIO { dao.delete(folder) }
                dataSet.removeAt(position)
                notifyItemRemoved(position)
            }
            .create()
            .show()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addItem(it: NotesFolders) {
        runOnIO {
            dataSet.add(it.copy(id = dao.insert(it)))
        }
        notifyItemInserted(dataSet.lastIndex)
    }

}