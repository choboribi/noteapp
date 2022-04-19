package com.example.notesapp

import androidx.room.Database
import androidx.room.RoomDatabase
import entities.NotesFolders
import entities.Note
import entities.FolderDao
import entities.NoteDao

@Database(entities = [Note::class, NotesFolders::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val name = "AppDatabase"
    }

    abstract fun noteDao(): NoteDao
    abstract fun folderDao(): FolderDao
}