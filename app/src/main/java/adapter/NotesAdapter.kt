package Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.NoteViewActivity
import com.example.notesapp.NotesActivity
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNotesBinding
import com.example.notesapp.runOnIO
import entities.Note
import entities.NoteDao
import kotlinx.coroutines.NonDisposableHandle.parent

class NotesAdapter(folderId: Long, private val dao:NoteDao) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var dataSet = mutableListOf<Note>()
    val folderId = folderId

    init {
        runOnIO { dataSet =  dao.getForSet(folderId).toMutableList()}
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val notesTitle : TextView = view.findViewById(R.id.notesTitle)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(viewGroup.context))
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.notesTitle.text = item.title
        viewHolder.itemView.setOnLongClickListener {
            showEditDialog(it.context, position)
            true
        }
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(viewHolder.itemView.context, NoteViewActivity::class.java)
            intent.putExtra("note", item.id)
            intent.putExtra("body", item.body)
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    private fun showEditDialog(context: Context, position: Int) {
        val customTitle = EditText(context)
        val notes = dataSet[position]
        customTitle.setText(notes.title)
        customTitle.textSize = 10 * context.resources.displayMetrics.scaledDensity
        AlertDialog.Builder(context)
            .setCustomTitle(customTitle)
            .setPositiveButton("Done") { _, _ ->
                notes.title = customTitle.text.toString()
                runOnIO { dao.update(notes) }
                notifyItemChanged(position)
            }
            .setNeutralButton("Share") { _,_ ->
                var share = Intent(android.content.Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(android.content.Intent.EXTRA_TEXT, notes.body)
                context.startActivity(Intent.createChooser(share, "Share with:"))

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