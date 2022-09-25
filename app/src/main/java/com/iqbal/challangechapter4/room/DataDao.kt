package com.iqbal.challangechapter4.room

import androidx.room.*


@Dao
interface DataDao {

    @Insert
    fun insertNote(note: DataNote)

    @Query("SELECT * FROM datanote ORDER BY id DESC")
    fun getDataNote() : List<DataNote>

    @Delete
    fun deletNote(note : DataNote) : Int

    @Update
    fun updateNote(note: DataNote)
}