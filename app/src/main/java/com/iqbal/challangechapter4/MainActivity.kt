package com.iqbal.challangechapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbal.challangechapter4.databinding.ActivityMainBinding
import com.iqbal.challangechapter4.room.DataNote
import com.iqbal.challangechapter4.room.NoteDataBases
import com.iqbal.challangechapter4.room.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}