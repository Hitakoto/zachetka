package com.example.zachetka.admin

import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import com.example.zachetka.R
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException

class EditUserModeActivity : AppCompatActivity() {

    lateinit var btnAddMain : Button
    lateinit var btnEditMain : Button
    lateinit var btnDeleteMain : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_mode)

        btnAddMain = findViewById(R.id.btn_add)
        btnEditMain = findViewById(R.id.btn_edit)
        btnDeleteMain = findViewById(R.id.btn_delete)

        btnAddMain.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(this, AddUserActivity::class.java))
        })

        btnEditMain.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(this, EditUserActivity::class.java))
        })

        btnDeleteMain.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(this, DeleteUserActivity::class.java))
        })

    }
}