package cs.mad.flashcards.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cs.mad.flashcards.R

/*
===================================================================================================================

     Reference documentation for recyclers: https://developer.android.com/guide/topics/ui/layout/recyclerview

===================================================================================================================
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
            connect to views using findViewById
            setup views here - recyclerview, button
            don't forget to notify the adapter if the data set is changed
         */

    }
}