package com.example.zachetka.student.studentMainFragments.month

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
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException

class MonthStudentFragment : Fragment() {

    lateinit var v : View

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    lateinit var linRet: LinearLayout
    lateinit var tableRet: TableLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_month_student, container, false)

        linRet = v.findViewById(R.id.linearRatingsMS)
        tableRet = v.findViewById(R.id.ratingsMS)

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

        val id : String? = activity?.intent?.getStringExtra("idUser")

        val cursor = database.rawQuery("SELECT Discipline.nameDis, MonthAttestation.month, MonthAttestation.grade FROM MonthAttestation, Discipline, Students WHERE MonthAttestation.idDiscipline = Discipline.idDiscipline AND Students.idStudent = MonthAttestation.idStudent AND Students.idUser = $id", null)
        if (cursor.moveToFirst()) {
            do {
                val row = TableRow(activity!!)
                val textDis = TextView(activity!!)
                textDis.text = cursor.getString(0).toString() + " "
                textDis.setTextColor(Color.rgb(24, 79, 154))
                textDis.gravity = Gravity.CENTER
                textDis.textAlignment = View.TEXT_ALIGNMENT_CENTER

                val textMonth = TextView(activity!!)
                textMonth.text = cursor.getString(1).toString() + " "
                textMonth.setTextColor(Color.rgb(24, 79, 154))
                textMonth.gravity = Gravity.CENTER
                textMonth.textAlignment = View.TEXT_ALIGNMENT_CENTER

                val textGrade = TextView(activity!!)
                textGrade.text = cursor.getInt(2).toString() + " "
                textGrade.setTextColor(Color.rgb(24, 79, 154))
                textGrade.gravity = Gravity.CENTER
                textGrade.textAlignment = View.TEXT_ALIGNMENT_CENTER

                row.addView(textDis, layoutParams)
                row.addView(textMonth, layoutParams)
                row.addView(textGrade, layoutParams)
                tableRet.addView(row)
            } while (cursor.moveToNext())
        } else Log.d("mLog", "0 rows")
        cursor.close()

        return v
    }
}