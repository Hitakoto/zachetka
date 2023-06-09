package com.example.zachetka.admin.adminMainFragments.home

import android.content.Intent
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.zachetka.R
import com.example.zachetka.admin.EditUserModeActivity
import com.example.zachetka.dbHelper.DBHelper
import com.example.zachetka.student.StudentRecordActivity
import java.io.IOException

class HomeAdminFragment : Fragment() {

    lateinit var v : View

    lateinit var linRet: LinearLayout
    lateinit var tableRet: TableLayout

    lateinit var btnEditMode: Button

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home_admin, container, false)

        linRet = v.findViewById(R.id.linearUsers)
        tableRet = v.findViewById(R.id.users)

        btnEditMode = v.findViewById(R.id.btnEditMode)

        btnEditMode.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(activity, EditUserModeActivity::class.java))
        })

        dbHelper = DBHelper(activity!!)

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

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

        val cursor = database.rawQuery("SELECT surname, firstname, patronymic, role FROM Users", null)
        if (cursor.moveToFirst()) {
            do {
                val row = TableRow(activity!!)
                val textSFP = TextView(activity!!)
                val textS : String = cursor.getString(0).toString() + " "
                val textF : String = cursor.getString(1).toString() + " "
                val textP : String = cursor.getString(2).toString() + " "
                textSFP.text = "$textS $textF $textP"
                textSFP.setTextColor(Color.rgb(24, 79, 154))
                textSFP.gravity = Gravity.CENTER
                textSFP.textAlignment = View.TEXT_ALIGNMENT_CENTER

                val textRole = TextView(activity!!)
                textRole.text = cursor.getString(3).toString() + " "
                textRole.setTextColor(Color.rgb(24, 79, 154))
                textRole.gravity = Gravity.CENTER
                textRole.textAlignment = View.TEXT_ALIGNMENT_CENTER

                row.addView(textSFP, layoutParams)
                row.addView(textRole, layoutParams)
                tableRet.addView(row)
            } while (cursor.moveToNext())
        } else Log.d("mLog", "0 rows")
        cursor.close()

        return v
    }
}