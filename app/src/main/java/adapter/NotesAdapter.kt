package Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.runOnIO
import entities.Note
import entities.NoteDao

class NotesAdapter(folderId: Long, private val dao:NoteDao) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val dataSet = mutableListOf<Note>()

    init {
        this.dataSet.addAll(dataSet)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val notesTitle : TextView = view.findViewById(R.id.notesTitle)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_notes, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.notesTitle.text = item.title
        viewHolder.itemView.setOnLongClickListener {
            showEditDialog(it.context, position)
            true
        }
    }

    private fun showEditDialog(context: Context, position: Int) {
        val customTitle = EditText(context)
        val notes = dataSet[position]
        customTitle.setText(notes.title)
        customTitle.textSize = 20 * context.resources.displayMetrics.scaledDensity
        AlertDialog.Builder(context)
            .setCustomTitle(customTitle)
            .setPositiveButton("Done") { _, _ ->
                notes.title = customTitle.text.toString()
                runOnIO { dao.update(notes) }
                notifyItemChanged(position)
            }
            .setNegativeButton("Delete") { _,_ ->
                runOnIO { dao.delete(notes) }
                dataSet.removeAt(position)
                notifyItemRemoved(position)
            }
            .create()
            .show()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addItem(it: Note){
        runOnIO {
            dataSet.add(it.copy(id = dao.insert(it)))
        }
        notifyItemInserted(dataSet.lastIndex)
    }
}