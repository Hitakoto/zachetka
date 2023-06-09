package com.example.zachetka.student.studentMainFragments.home

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
import com.example.zachetka.student.StudentRecordActivity
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException

class HomeStudentFragment : Fragment() {

    lateinit var ivbRecord : ImageView
    lateinit var v : View

    lateinit var linRet: LinearLayout
    lateinit var tableRet: TableLayout

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home_student, container, false)

        ivbRecord = v.findViewById(R.id.ivb_recS)

        ivbRecord.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(activity, StudentRecordActivity::class.java))
        })

        linRet = v.findViewById(R.id.linearRatingsS)
        tableRet = v.findViewById(R.id.ratingsS)

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

        val cursor = database.rawQuery("SELECT Discipline.nameDis, MonthAttestation.grade FROM MonthAttestation, Discipline, Students WHERE MonthAttestation.idDiscipline = Discipline.idDiscipline AND Students.idStudent = MonthAttestation.idStudent", null)
        if (cursor.moveToFirst()) {
            do {
                val row = TableRow(activity!!)
                val textDis = TextView(activity!!)
                textDis.text = cursor.getString(0).toString() + " "
                textDis.setTextColor(Color.rgb(24, 79, 154))
                textDis.gravity = Gravity.CENTER
                textDis.textAlignment = View.TEXT_ALIGNMENT_CENTER

                val textGrade = TextView(activity!!)
                textGrade.text = cursor.getInt(1).toString() + " "
                textGrade.setTextColor(Color.rgb(24, 79, 154))
                textGrade.gravity = Gravity.CENTER
                textGrade.textAlignment = View.TEXT_ALIGNMENT_CENTER

                row.addView(textDis, layoutParams)
                row.addView(textGrade, layoutParams)
                tableRet.addView(row)
            } while (cursor.moveToNext())
        } else Log.d("mLog", "0 rows")
        cursor.close()

        return v
    }
}