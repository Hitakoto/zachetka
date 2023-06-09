package com.example.zachetka.teacher

import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zachetka.R
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException

class TeacherRecordActivity : AppCompatActivity() {

    lateinit var linRec: LinearLayout
    lateinit var tableRec: TableLayout

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_record)

        linRec = findViewById(R.id.linearRecT)
        tableRec = findViewById(R.id.tableRecT)

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

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

        //SELECT Discipline.nameDis, FormsAttestations.title, PromezhAttestation.date, PromezhAttestation.grade FROM PromezhAttestation, Discipline, Students, FormsAttestations, Users, Groups, Teachers WHERE Users.idUser = Students.idUser AND Discipline.idDiscipline = PromezhAttestation.idDiscipline AND PromezhAttestation.idStudent = Students.idStudent AND FormsAttestations.idForm = PromezhAttestation.idForm AND Students.idGroup = Groups.idGroup AND Groups.idTeacher = Teachers.idTeacher AND Teachers.idUser = Users.idUser AND Users.idUser = $id

        val cursor = database.rawQuery("SELECT Discipline.nameDis, FormsAttestations.title, PromezhAttestation.date, PromezhAttestation.grade FROM PromezhAttestation, Discipline, Students, FormsAttestations, Users WHERE Users.idUser = Students.idUser AND Discipline.idDiscipline = PromezhAttestation.idDiscipline AND PromezhAttestation.idStudent = Students.idStudent AND FormsAttestations.idForm = PromezhAttestation.idForm", null)
        if (cursor.moveToFirst()) {
            do {
                val row = TableRow(this)
                val textDis = TextView(this)
                textDis.text = cursor.getString(0).toString() + " "
                textDis.setTextColor(Color.rgb(24, 79, 154))
                textDis.gravity = Gravity.CENTER
                textDis.textAlignment = View.TEXT_ALIGNMENT_CENTER

                val textForm = TextView(this)
                textForm.text = cursor.getString(1).toString() + " "
                textForm.setTextColor(Color.rgb(24, 79, 154))
                textForm.gravity = Gravity.CENTER
                textForm.textAlignment = View.TEXT_ALIGNMENT_CENTER

                val textExam = TextView(this)
                textExam.text = cursor.getString(2).toString() + " "
                textExam.setTextColor(Color.rgb(24, 79, 154))
                textExam.gravity = Gravity.CENTER
                textExam.textAlignment = View.TEXT_ALIGNMENT_CENTER

                val textGrade = TextView(this)
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
}