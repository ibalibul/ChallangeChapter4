package com.iqbal.challangechapter4.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataNote::class], version = 1)
abstract class NoteDataBases : RoomDatabase() {

    abstract fun noteDao () : DataDao

    companion object{

        private var INSTANCE : NoteDataBases? = null

        fun getInstance(context : Context):NoteDataBases? {
            if (INSTANCE == null){
                synchronized(NoteDataBases::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteDataBases::class.java,"Note.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}