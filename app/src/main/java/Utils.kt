package com.example.notesapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val PARENT_SET_ID_TAG = "parentSetId"

// use this for database calls!!!
fun runOnIO(lambda: suspend () -> Unit) {
    runBlocking {
        launch(Dispatchers.IO) { lambda() }
    }
}