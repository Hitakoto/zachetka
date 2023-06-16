package com.example.zachetka.admin

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

     lateinit var edit_mode_text: EditText
     lateinit var spinnerUser: Spinner
     lateinit var surnameE: EditText
     lateinit var nameE: EditText
     lateinit var patronymicE: EditText
     lateinit var loginE: EditText
     lateinit var passwordE: EditText
     lateinit var spinnerRoleUser: Spinner
     lateinit var spinnerGroupUser: Spinner
     lateinit var spinnerEditMode: Spinner
     lateinit var btn_edit: Button

     var selectedAttribute: String? = null

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_mode)

        edit_mode_text = findViewById(R.id.edit_mode_text)
        spinnerUser = findViewById(R.id.spinnerUser)
        surnameE = findViewById(R.id.surnameE)
        nameE = findViewById(R.id.nameE)
        patronymicE = findViewById(R.id.patronymicE)
        loginE = findViewById(R.id.loginE)
        passwordE = findViewById(R.id.passwordE)
        spinnerRoleUser = findViewById(R.id.spinnerRoleUser)
        spinnerGroupUser = findViewById(R.id.spinnerGroupUser)
        spinnerEditMode = findViewById(R.id.spinnerEditMode)
        btn_edit = findViewById(R.id.btn_edit)

        dbHelper = DBHelper(this)

        try {
            dbHelper.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }

        try {
            database = dbHelper.writableDatabase
        } catch (mIOException: SQLException) {
            throw mIOException
        }

        //User
        val cursorUsers: Cursor = database.rawQuery("SELECT id, surname, firstname, patronymic FROM Users ORDER BY surname", null)
        val users = ArrayList<String>()
        if (cursorUsers.moveToFirst()) {
            do {
                users.add(
                    java.lang.String.valueOf(cursorUsers.getString(0)).toString() + " "
                            + cursorUsers.getString(1).toString() + " "
                            + cursorUsers.getString(2).toString().substring(0, 1) + " "
                            + cursorUsers.getString(3).toString().substring(0, 1) + " "
                )
            } while (cursorUsers.moveToNext())
        } else Log.d("mLog", "0 rows")
        val adapterUsers: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users)
        adapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUser.adapter = adapterUsers
        cursorUsers.close()

        var userName: String = spinnerUser.selectedItem.toString()

        //Role
        val options = arrayOf("Администратор", "Преподаватель", "Студент")
        val adapterRoles: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options)
        adapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRoleUser.adapter = adapterRoles

        var roleName: String = spinnerRoleUser.selectedItem.toString()

        //Group
        val cursorGroup: Cursor = database.rawQuery("SELECT title FROM Groups ORDER BY surname", null)
        val group = ArrayList<String>()
        if (cursorGroup.moveToFirst()) {
            do {
                group.add(
                    java.lang.String.valueOf(cursorGroup.getString(0)).toString() + " "
                )
            } while (cursorUsers.moveToNext())
        } else Log.d("mLog", "0 rows")
        val adapterGroup: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, group)
        adapterGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGroupUser.adapter = adapterGroup
        cursorGroup.close()

        var groupName: String = spinnerGroupUser.selectedItem.toString()

        //Mode
        val options1 = arrayOf("Добавить", "Изменить", "Удалить")
        val adapterMode: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options1)
        adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEditMode.adapter = adapterMode

        var modeName: String = spinnerEditMode.selectedItem.toString()

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

        //UserS
        spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                userName = spinnerUser.selectedItem.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        //RoleS
        spinnerRoleUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (parent.getItemAtPosition(position).toString()) {
                    "Администратор" -> {
                        spinnerGroupUser.visibility = View.GONE
                    }
                    "Преподаватель" -> {
                        spinnerGroupUser.visibility = View.GONE
                    }
                    "Студент" -> {
                        spinnerGroupUser.visibility = View.VISIBLE
                    }
                }
                roleName = spinnerRoleUser.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        //GroupS
        spinnerGroupUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                groupName = spinnerGroupUser.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        //ModeS
        spinnerEditMode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (parent.getItemAtPosition(position).toString()) {
                    "Добавление" -> {
                        spinnerUser.visibility = View.GONE
                    }
                    "Изменение" -> {
                        spinnerUser.visibility = View.VISIBLE
                    }
                    "Удаление" -> {
                        spinnerUser.visibility = View.VISIBLE
                    }
                }

                modeName = spinnerEditMode.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        btn_edit.setOnClickListener(View.OnClickListener { _ ->
            //ModeS
            spinnerEditMode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    when (parent.getItemAtPosition(position).toString()) {
                        "Добавление" -> {
                            //UserS
                            spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                                    val selectedItem = parent.getItemAtPosition(position).toString()

                                    userName = spinnerUser.selectedItem.toString()

                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                    // Действия, когда ничего не выбрано
                                }
                            }
                        }
                        "Изменение" -> {
                            //UserS
                            spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                                    val selectedItem = parent.getItemAtPosition(position).toString()

                                    userName = spinnerUser.selectedItem.toString()

                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                    // Действия, когда ничего не выбрано
                                }
                            }
                        }
                        "Удаление" -> {
                            //UserS
                            spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                                    val selectedItem = parent.getItemAtPosition(position).toString()

                                    userName = spinnerUser.selectedItem.toString()

                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                    // Действия, когда ничего не выбрано
                                }
                            }
                        }
                    }

                    modeName = spinnerEditMode.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Действия, когда ничего не выбрано
                }
            }
        })

    }
}