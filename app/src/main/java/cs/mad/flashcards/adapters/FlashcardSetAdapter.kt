package cs.mad.flashcards.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cs.mad.flashcards.R

class FlashcardSetAdapter() :
    RecyclerView.Adapter<FlashcardSetAdapter.ViewHolder>() {

    // need to get and store a reference to the data set

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // store view references as properties using findViewById on view
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        /*
            this is written for you but understand what is happening here
            the layout for an individual item is being inflated
            the inflated layout is passed to view holder for storage

            THE ITEM LAYOUT STILL NEEDS TO BE SETUP IN THE LAYOUT EDITOR
         */
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_flashcard_set, viewGroup, false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        /*
            fill the contents of the view using references in view holder and current position in data set

            to launch FlashcardSetDetailActivity ->
            viewHolder.itemView.context.startActivity(Intent(viewHolder.itemView.context, FlashcardSetDetailActivity::class.java))
         */
    }

    override fun getItemCount(): Int {
        // return the size of the data set
        return -1
    }
}