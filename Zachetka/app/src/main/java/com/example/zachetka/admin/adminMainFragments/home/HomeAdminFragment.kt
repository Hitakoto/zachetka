package com.example.zachetka.admin.adminMainFragments.home

import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.Typeface
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

    lateinit var spinnerRole: Spinner
    var selectedAttribute: String? = null

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home_admin, container, false)

        linRet = v.findViewById(R.id.linearUsers)
        tableRet = v.findViewById(R.id.users)

        spinnerRole = v.findViewById(R.id.spinnerRoleA)

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

        val options = arrayOf("Администратор", "Преподаватель", "Студент")
        val adapterRoles: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, options)
        adapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapterRoles

        var roleName: String = spinnerRole.selectedItem.toString()

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

        spinnerRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                if (selectedAttribute != selectedItem) {
                    // Выбран новый атрибут, сбрасываем данные прошлого атрибута
                    tableRet.removeAllViews()
                    val rowTitle = TableRow(activity!!)
                    val disciplineT = TextView(activity!!)
                    disciplineT.text = "ФИО"
                    disciplineT.setTextColor(Color.rgb(24, 79, 154))
                    disciplineT.gravity = Gravity.CENTER
                    disciplineT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    disciplineT.textSize = 16f
                    disciplineT.setPadding(3,3,3,3,)
                    disciplineT.setTypeface(null, Typeface.BOLD)
                    //disciplineT.typeface = customTypeface

                    val gradeT = TextView(activity!!)
                    gradeT.text = "Роль"
                    gradeT.setTextColor(Color.rgb(24, 79, 154))
                    gradeT.gravity = Gravity.CENTER
                    gradeT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    gradeT.textSize = 16f
                    gradeT.setPadding(3,3,3,3)
                    gradeT.setTypeface(null, Typeface.BOLD)
                    //gradeT.typeface = customTypeface

                    rowTitle.addView(disciplineT, layoutParams)
                    rowTitle.addView(gradeT, layoutParams)
                    tableRet.addView(rowTitle)

                    selectedAttribute = selectedItem
                }

                roleName = spinnerRole.selectedItem.toString()

                val cursor = database.rawQuery("SELECT surname, firstname, patronymic, role FROM Users WHERE role = '$roleName' ORDER BY role", null)
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
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        return v
    }
}