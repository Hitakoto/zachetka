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

class EditUserActivity : AppCompatActivity() {

    lateinit var edit_mode_text: TextView
    lateinit var surnameE: EditText
    lateinit var nameE: EditText
    lateinit var patronymicE: EditText
    lateinit var loginE: EditText
    lateinit var passwordE: EditText
    lateinit var spinnerUser: Spinner
    lateinit var spinnerRoleUser: Spinner
    lateinit var spinnerGroupUser: Spinner
    lateinit var btn_edit: Button
    lateinit var context: Context

    var selectedAttribute: String? = null

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        context = this

        edit_mode_text = findViewById(R.id.edit_mode_text_edit)
        surnameE = findViewById(R.id.surnameEdit)
        nameE = findViewById(R.id.nameEdit)
        patronymicE = findViewById(R.id.patronymicEdit)
        loginE = findViewById(R.id.loginEdit)
        passwordE = findViewById(R.id.passwordEdit)
        spinnerUser = findViewById(R.id.spinnerUserEdit)
        spinnerRoleUser = findViewById(R.id.spinnerRoleUserEdit)
        spinnerGroupUser = findViewById(R.id.spinnerGroupUserEdit)
        btn_edit = findViewById(R.id.btn_edit_user)

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
        val cursorUsers: Cursor = database.rawQuery("SELECT idUser, surname, firstname, patronymic FROM Users ORDER BY idUser", null)
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
        val adapterUsers: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.list_item_spinner, users)
        adapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUser.adapter = adapterUsers
        cursorUsers.close()

        var userName: String = spinnerUser.selectedItem.toString()

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

        var idU: String? = null

        //UserS
        spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                val selectedId: List<String> = spinnerUser.selectedItem.toString().split(" ")

                if (selectedAttribute != selectedItem) {
                    val sql = "SELECT * FROM Users WHERE idUser = '" + selectedId[0] + "'"
                    val cursor = database.rawQuery(sql, null)
                    val usersE = HashMap<String, Any>()
                    cursor.moveToFirst()
                    while (!cursor.isAfterLast) {
                        usersE["idUser"] = cursor.getString(0)
                        usersE["surname"] = cursor.getString(1)
                        usersE["firstname"] = cursor.getString(2)
                        usersE["patronymic"] = cursor.getString(3)
                        usersE["login"] = cursor.getString(4)
                        usersE["password"] = cursor.getString(5)
                        cursor.moveToNext()
                    }
                    idU = usersE["idUser"].toString()
                    surnameE.setText(usersE["surname"].toString())
                    nameE.setText(usersE["firstname"].toString())
                    patronymicE.setText(usersE["patronymic"].toString())
                    loginE.setText(usersE["login"].toString())
                    passwordE.setText(usersE["password"].toString())
                }

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

        btn_edit.setOnClickListener(View.OnClickListener { _ ->
            if (surnameE.text.toString() == "" || nameE.text.toString() == "" || patronymicE.text.toString() == "" || loginE.text.toString() == "" || passwordE.text.toString() == "") {
                Toast.makeText(this, "Обнаружены пустые поля", Toast.LENGTH_LONG).show()
            } else {
                val queryEdit = "UPDATE Users SET surname = '" + surnameE.text.toString() + "', firstname = '" + nameE.text.toString() + "', patronymic = '" + patronymicE.text.toString() + "', login = '" + loginE.text.toString() + "', password = '" + passwordE.text.toString() + "' WHERE idUser = '" + idU + "'"
                database.execSQL(queryEdit)
                Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, AdminMainActivity::class.java))
            }
        })
    }
}