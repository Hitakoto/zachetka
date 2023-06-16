package com.example.zachetka.admin

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.zachetka.R
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException
import java.sql.SQLException


class AddUserActivity : AppCompatActivity() {

    lateinit var edit_mode_text: TextView
    lateinit var surnameE: EditText
    lateinit var nameE: EditText
    lateinit var patronymicE: EditText
    lateinit var loginE: EditText
    lateinit var passwordE: EditText
    lateinit var spinnerRoleUser: Spinner
    lateinit var spinnerGroupUser: Spinner
    lateinit var btn_edit: Button
    lateinit var context: Context

    var selectedAttribute: String? = null

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        context = this

        edit_mode_text = findViewById(R.id.edit_mode_text_add)
        surnameE = findViewById(R.id.surnameAdd)
        nameE = findViewById(R.id.nameAdd)
        patronymicE = findViewById(R.id.patronymicAdd)
        loginE = findViewById(R.id.loginAdd)
        passwordE = findViewById(R.id.passwordAdd)
        spinnerRoleUser = findViewById(R.id.spinnerRoleUserAdd)
        spinnerGroupUser = findViewById(R.id.spinnerGroupUserAdd)
        btn_edit = findViewById(R.id.btn_add_user)

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

        //Role
        val options = arrayOf("Администратор", "Преподаватель", "Студент")
        val adapterRoles: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.list_item_spinner, options)
        adapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRoleUser.adapter = adapterRoles

        var roleName: String = spinnerRoleUser.selectedItem.toString()

        //Group
        val cursorGroups: Cursor = database.rawQuery("SELECT title FROM Groups ORDER BY title", null)
        val groups = ArrayList<String>()
        if (cursorGroups.moveToFirst()) {
            do {
                groups.add(
                    java.lang.String.valueOf(cursorGroups.getString(0)).toString() + " "
                )
            } while (cursorGroups.moveToNext())
        } else Log.d("mLog", "0 rows")
        val adapterGroups: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.list_item_spinner, groups)
        adapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGroupUser.adapter = adapterGroups
        cursorGroups.close()

        var groupName: String = spinnerGroupUser.selectedItem.toString()

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

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

        btn_edit.setOnClickListener(View.OnClickListener { _ ->
            if (surnameE.text.toString() == "" || nameE.text.toString() == "" || patronymicE.text.toString() == "" || loginE.text.toString() == "" || passwordE.text.toString() == "") {
                Toast.makeText(this, "Обнаружены пустые поля", Toast.LENGTH_LONG).show()
            } else {
                val query = "INSERT INTO Users (surname, firstname, patronymic, login, password, role) VALUES ('" + surnameE.text.toString() + "', '" + nameE.text.toString() + "', '" + patronymicE.text.toString() + "', '" + loginE.text.toString() + "', '" + passwordE.text.toString() + "', '" + roleName + "')"
                database.execSQL(query)

                when (roleName) {
                    "Студент" -> {
                        val cursor = database.rawQuery("SELECT last_insert_rowid()", null)
                        cursor.moveToFirst()
                        val insertedId = cursor.getLong(0)
                        cursor.close()

                        val queryFK =
                            "INSERT INTO Students (idUser, idGroup) VALUES ('$insertedId', '1')"
                        database.execSQL(queryFK)

                    }
                    "Администратор" -> {
                        val cursor = database.rawQuery("SELECT last_insert_rowid()", null)
                        cursor.moveToFirst()
                        val insertedId = cursor.getLong(0)
                        cursor.close()

                        val queryFK = "INSERT INTO SysAdmin (idUser) VALUES ('$insertedId')"
                        database.execSQL(queryFK)

                    }
                    "Преподаватель" -> {
                        val cursor = database.rawQuery("SELECT last_insert_rowid()", null)
                        cursor.moveToFirst()
                        val insertedId = cursor.getLong(0)
                        cursor.close()

                        val queryFK = "INSERT INTO Teachers (idUser) VALUES ('$insertedId')"
                        database.execSQL(queryFK)
                    }
                }
                Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, AdminMainActivity::class.java))
            }
        })
    }
}