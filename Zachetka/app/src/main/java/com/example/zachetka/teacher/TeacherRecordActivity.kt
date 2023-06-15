package com.example.zachetka.teacher

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.zachetka.R
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException

class TeacherRecordActivity : AppCompatActivity() {

    lateinit var linRec: LinearLayout
    lateinit var tableRec: TableLayout

    lateinit var spinnerStudentsRBT: Spinner
    lateinit var spinnerGroupsRBT: Spinner
    var selectedAttribute: String? = null

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    lateinit var context: Context;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_record)

        linRec = findViewById(R.id.linearRecT)
        tableRec = findViewById(R.id.tableRecT)

        spinnerStudentsRBT = findViewById(R.id.spinnerStudentsRBT)
        spinnerGroupsRBT = findViewById(R.id.spinnerGroupsRBT)

        context = this

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

        val cursorUsers1: Cursor = database.rawQuery("SELECT surname, firstname, patronymic FROM Users, Students WHERE Users.idUser = Students.idUser ORDER BY surname", null)
        val users1 = ArrayList<String>()
        if (cursorUsers1.moveToFirst()) {
            do {
                users1.add(
                    java.lang.String.valueOf(cursorUsers1.getString(0)).toString() + " "
                            + cursorUsers1.getString(1).toString().substring(0, 1) + " "
                            + cursorUsers1.getString(2).toString().substring(0, 1) + " "
                )
            } while (cursorUsers1.moveToNext())
        } else Log.d("mLog", "0 rows")
        val adapterUsers1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users1)
        adapterUsers1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStudentsRBT.adapter = adapterUsers1
        cursorUsers1.close()

        val cursorGroups1: Cursor = database.rawQuery("SELECT title FROM Groups ORDER BY title", null)
        val groups1 = ArrayList<String>()
        if (cursorGroups1.moveToFirst()) {
            do {
                groups1.add(
                    java.lang.String.valueOf(cursorGroups1.getString(0)).toString() + " "
                )
            } while (cursorGroups1.moveToNext())
        } else Log.d("mLog", "0 rows")
        val adapterGroups1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups1)
        adapterGroups1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGroupsRBT.adapter = adapterGroups1
        cursorGroups1.close()

        var studentName: String = spinnerStudentsRBT.selectedItem.toString()
        var groupsName: String = spinnerGroupsRBT.selectedItem.toString()

        val cursorUsers: Cursor = database.rawQuery("SELECT surname, firstname, patronymic FROM Users, Students, Groups WHERE Users.idUser = Students.idUser AND Students.idGroup = Groups.idGroup AND Groups.title = '" + groupsName.substring(0, groupsName.length - 1) +"' ORDER BY surname", null)
        val users = ArrayList<String>()
        if (cursorUsers.moveToFirst()) {
            do {
                users.add(
                    java.lang.String.valueOf(cursorUsers.getString(0)).toString() + " "
                            + cursorUsers.getString(1).toString().substring(0, 1) + " "
                            + cursorUsers.getString(2).toString().substring(0, 1) + " "
                )
            } while (cursorUsers.moveToNext())
        } else Log.d("mLog", "0 rows")
        val adapterUsers: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users)
        adapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStudentsRBT.adapter = adapterUsers
        cursorUsers.close()

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

        spinnerStudentsRBT.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                if (selectedAttribute != selectedItem) {
                    // Выбран новый атрибут, сбрасываем данные прошлого атрибута
                    tableRec.removeAllViews()
                    val rowTitle = TableRow(context)
                    val disciplineT = TextView(context)
                    disciplineT.text = "Дисциплина"
                    disciplineT.setTextColor(Color.rgb(24, 79, 154))
                    disciplineT.gravity = Gravity.CENTER
                    disciplineT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    disciplineT.textSize = 16f
                    disciplineT.setPadding(3,3,3,3,)
                    disciplineT.setTypeface(null, Typeface.BOLD)
                    //disciplineT.typeface = customTypeface

                    val formT = TextView(context)
                    formT.text = "Форма аттестации"
                    formT.setTextColor(Color.rgb(24, 79, 154))
                    formT.gravity = Gravity.CENTER
                    formT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    formT.textSize = 16f
                    formT.setPadding(3,3,3,3)
                    formT.setTypeface(null, Typeface.BOLD)
                    //formT.typeface = customTypeface

                    val dateT = TextView(context)
                    dateT.text = "Дата экзамена"
                    dateT.setTextColor(Color.rgb(24, 79, 154))
                    dateT.gravity = Gravity.CENTER
                    dateT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    dateT.textSize = 16f
                    dateT.setPadding(3,3,3,3)
                    dateT.setTypeface(null, Typeface.BOLD)
                    //dateT.typeface = customTypeface

                    val gradeT = TextView(context)
                    gradeT.text = "Оценка"
                    gradeT.setTextColor(Color.rgb(24, 79, 154))
                    gradeT.gravity = Gravity.CENTER
                    gradeT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    gradeT.textSize = 16f
                    gradeT.setPadding(3,3,3,3)
                    gradeT.setTypeface(null, Typeface.BOLD)
                    //gradeT.typeface = customTypeface

                    rowTitle.addView(disciplineT, layoutParams)
                    rowTitle.addView(formT, layoutParams)
                    rowTitle.addView(dateT, layoutParams)
                    rowTitle.addView(gradeT, layoutParams)
                    tableRec.addView(rowTitle)

                    selectedAttribute = selectedItem
                }

                studentName = spinnerStudentsRBT.selectedItem.toString()
                groupsName = spinnerGroupsRBT.selectedItem.toString()

                val cursor = database.rawQuery("SELECT Discipline.nameDis, FormsAttestations.title, PromezhAttestation.date, PromezhAttestation.grade FROM PromezhAttestation, Discipline, Students, FormsAttestations, Users, Groups WHERE Users.idUser = Students.idUser AND Discipline.idDiscipline = PromezhAttestation.idDiscipline AND PromezhAttestation.idStudent = Students.idStudent AND FormsAttestations.idForm = PromezhAttestation.idForm AND Students.idUser = Users.idUser AND Users.surname = '" + studentName.substring(0, studentName.length - 5) + "' AND Groups.idGroup = Students.idGroup AND Groups.title = '"+ groupsName.substring(0, groupsName.length - 1) + "'", null)
                if (cursor.moveToFirst()) {
                    do {
                        val row = TableRow(context)
                        val textDis = TextView(context)
                        textDis.text = cursor.getString(0).toString() + " "
                        textDis.setTextColor(Color.rgb(24, 79, 154))
                        textDis.gravity = Gravity.CENTER
                        textDis.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        val textForm = TextView(context)
                        textForm.text = cursor.getString(1).toString() + " "
                        textForm.setTextColor(Color.rgb(24, 79, 154))
                        textForm.gravity = Gravity.CENTER
                        textForm.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        val textExam = TextView(context)
                        textExam.text = cursor.getString(2).toString() + " "
                        textExam.setTextColor(Color.rgb(24, 79, 154))
                        textExam.gravity = Gravity.CENTER
                        textExam.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        val textGrade = TextView(context)
                        textGrade.text = cursor.getInt(3).toString() + " "
                        textGrade.setTextColor(Color.rgb(24, 79, 154))
                        textGrade.gravity = Gravity.CENTER
                        textGrade.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        row.addView(textDis, layoutParams)
                        row.addView(textForm, layoutParams)
                        row.addView(textExam, layoutParams)
                        row.addView(textGrade, layoutParams)
                        tableRec.addView(row)
                    } while (cursor.moveToNext())
                } else Log.d("mLog", "0 rows")
                cursor.close()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        spinnerGroupsRBT.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                studentName = spinnerStudentsRBT.selectedItem.toString()
                groupsName = spinnerGroupsRBT.selectedItem.toString()

                val cursorUsers: Cursor = database.rawQuery("SELECT surname, firstname, patronymic FROM Users, Students, Groups WHERE Users.idUser = Students.idUser AND Students.idGroup = Groups.idGroup AND Groups.title = '" + groupsName.substring(0, groupsName.length - 1) +"' ORDER BY surname", null)
                val users = ArrayList<String>()
                if (cursorUsers.moveToFirst()) {
                    do {
                        users.add(
                            java.lang.String.valueOf(cursorUsers.getString(0)).toString() + " "
                                    + cursorUsers.getString(1).toString().substring(0, 1) + " "
                                    + cursorUsers.getString(2).toString().substring(0, 1) + " "
                        )
                    } while (cursorUsers.moveToNext())
                } else Log.d("mLog", "0 rows")
                val adapterUsers: ArrayAdapter<String> = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, users)
                adapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerStudentsRBT.adapter = adapterUsers
                cursorUsers.close()

                if (selectedAttribute != selectedItem) {
                    // Выбран новый атрибут, сбрасываем данные прошлого атрибута
                    tableRec.removeAllViews()
                    val rowTitle = TableRow(context)
                    val disciplineT = TextView(context)
                    disciplineT.text = "Дисциплина"
                    disciplineT.setTextColor(Color.rgb(24, 79, 154))
                    disciplineT.gravity = Gravity.CENTER
                    disciplineT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    disciplineT.textSize = 16f
                    disciplineT.setPadding(3,3,3,3,)
                    disciplineT.setTypeface(null, Typeface.BOLD)
                    //disciplineT.typeface = customTypeface

                    val gradeT = TextView(context)
                    gradeT.text = "Оценка"
                    gradeT.setTextColor(Color.rgb(24, 79, 154))
                    gradeT.gravity = Gravity.CENTER
                    gradeT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    gradeT.textSize = 16f
                    gradeT.setPadding(3,3,3,3)
                    gradeT.setTypeface(null, Typeface.BOLD)
                    //gradeT.typeface = customTypeface

                    rowTitle.addView(disciplineT, layoutParams)
                    rowTitle.addView(gradeT, layoutParams)
                    tableRec.addView(rowTitle)

                    selectedAttribute = selectedItem
                }

                val cursor = database.rawQuery("SELECT Discipline.nameDis, FormsAttestations.title, PromezhAttestation.date, PromezhAttestation.grade FROM PromezhAttestation, Discipline, Students, FormsAttestations, Users, Groups WHERE Users.idUser = Students.idUser AND Discipline.idDiscipline = PromezhAttestation.idDiscipline AND PromezhAttestation.idStudent = Students.idStudent AND FormsAttestations.idForm = PromezhAttestation.idForm AND Students.idUser = Users.idUser AND Users.surname = '" + studentName.substring(0, studentName.length - 5) + "' AND Groups.idGroup = Students.idGroup AND Groups.title = '"+ groupsName.substring(0, groupsName.length - 1) + "'", null)
                if (cursor.moveToFirst()) {
                    do {
                        val row = TableRow(context)
                        val textDis = TextView(context)
                        textDis.text = cursor.getString(0).toString() + " "
                        textDis.setTextColor(Color.rgb(24, 79, 154))
                        textDis.gravity = Gravity.CENTER
                        textDis.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        val textForm = TextView(context)
                        textForm.text = cursor.getString(1).toString() + " "
                        textForm.setTextColor(Color.rgb(24, 79, 154))
                        textForm.gravity = Gravity.CENTER
                        textForm.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        val textExam = TextView(context)
                        textExam.text = cursor.getString(2).toString() + " "
                        textExam.setTextColor(Color.rgb(24, 79, 154))
                        textExam.gravity = Gravity.CENTER
                        textExam.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        val textGrade = TextView(context)
                        textGrade.text = cursor.getInt(3).toString() + " "
                        textGrade.setTextColor(Color.rgb(24, 79, 154))
                        textGrade.gravity = Gravity.CENTER
                        textGrade.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        row.addView(textDis, layoutParams)
                        row.addView(textForm, layoutParams)
                        row.addView(textExam, layoutParams)
                        row.addView(textGrade, layoutParams)
                        tableRec.addView(row)
                    } while (cursor.moveToNext())
                } else Log.d("mLog", "0 rows")
                cursor.close()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }
    }
}