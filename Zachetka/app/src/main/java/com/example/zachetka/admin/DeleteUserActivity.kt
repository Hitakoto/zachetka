package com.example.zachetka.admin

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.zachetka.R
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException
import java.sql.SQLException

class DeleteUserActivity : AppCompatActivity() {

    lateinit var edit_mode_text: TextView
    lateinit var spinnerUser: Spinner
    lateinit var btn_delete: Button
    lateinit var context: Context

    var selectedAttribute: String? = null

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_user)

        context = this

        edit_mode_text = findViewById(R.id.edit_mode_text_delete)
        spinnerUser = findViewById(R.id.spinnerUserDelete)
        btn_delete = findViewById(R.id.btn_delete_user)

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
                        cursor.moveToNext()
                    }
                    idU = usersE["idUser"].toString()
                }

                userName = spinnerUser.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        btn_delete.setOnClickListener(View.OnClickListener { _ ->
            val queryDelete = "DELETE FROM Users WHERE idUser = '$idU'"
            database.execSQL(queryDelete)
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, AdminMainActivity::class.java))
        })
    }
}