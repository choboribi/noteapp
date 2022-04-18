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
import entities.Note

class NotesAdapter(dataset: List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

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
        viewHolder.itemView.setOnClickListener {
            AlertDialog.Builder(it.context)
                .setTitle(item.title)
                .setMessage(item.text)
                .setPositiveButton("Done") {_,_ ->}
                .setNeutralButton("Edit") {_,_ -> showEditDialog(it.context, item) }
                .create()
                .show()
        }
        viewHolder.itemView.setOnLongClickListener {
            showEditDialog(it.context, item)
            true
        }
    }

    private fun showEditDialog(context: Context, notes: Note) {
        val customTitle = EditText(context)
        val customBody = EditText(context)
        customTitle.setText(notes.title)
        customTitle.textSize = 20 * context.resources.displayMetrics.scaledDensity
        customBody.setText(notes.text)
        AlertDialog.Builder(context)
            .setCustomTitle(customTitle)
            .setView(customBody)
            .setPositiveButton("Done") { _,_ -> }
            .setNegativeButton("Delete") { _,_ -> }
            .create()
            .show()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addNote(it: Note){
        dataSet.add(it)
        notifyItemInserted(dataSet.lastIndex)
    }
}