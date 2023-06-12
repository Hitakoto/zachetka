package com.example.zachetka.teacher.teacherMainFragments.home

import android.content.Context
import android.content.Intent
import android.database.Cursor
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
import com.example.zachetka.teacher.TeacherRecordActivity
import java.io.IOException


class HomeTeacherFragment : Fragment() {

    lateinit var ivbRecord : ImageView
    lateinit var v : View

    lateinit var linRet: LinearLayout
    lateinit var tableRet: TableLayout

    lateinit var titleMonthName: TextView

    lateinit var spinnerStudentsMain: Spinner
    lateinit var spinnerGroupsMain: Spinner
    lateinit var spinnerCourseMain: Spinner

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home_teacher, container, false)

        ivbRecord = v.findViewById(R.id.ivb_recT)
        titleMonthName = v.findViewById(R.id.titleUsersT)

        spinnerStudentsMain = v.findViewById(R.id.spinnerStudentsMain)
        spinnerGroupsMain = v.findViewById(R.id.spinnerGroupsMain)
        spinnerCourseMain = v.findViewById(R.id.spinnerCourseMain)

        val id : String? = activity?.intent?.getStringExtra("idUser")

        ivbRecord.setOnClickListener(View.OnClickListener { _ ->
            var intentTRA = Intent(activity, TeacherRecordActivity::class.java)
            intentTRA.putExtra("idUser", id.toString());
            startActivity(intentTRA)
        })

        linRet = v.findViewById(R.id.linearRatingsT)
        tableRet = v.findViewById(R.id.ratingsT)

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

        val cursorSpinner: Cursor = database.rawQuery("SELECT surname, firstname, patronymic FROM User", null)
        val users = ArrayList<Any>()
        if (cursorSpinner.moveToFirst()) {
            do {
                users.add(
                    java.lang.String.valueOf(cursorSpinner.getString(0)).toString() + " "
                            + cursorSpinner.getString(1).toString() + " "
                            + cursorSpinner.getString(2).toString() + " "
                )
            } while (cursorSpinner.moveToNext())
        } else Log.d("mLog", "0 rows")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, users)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStudentsMain.adapter = adapter
        cursorSpinner.close()

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

        //SELECT Discipline.nameDis, MonthAttestation.grade, MonthAttestation.month FROM MonthAttestation, Discipline, Students, Users WHERE MonthAttestation.idDiscipline = Discipline.idDiscipline AND Students.idStudent = MonthAttestation.idStudent AND Students.idUser = Users.idUser AND Users.firstname = "Павел"
        val cursor = database.rawQuery("SELECT Discipline.nameDis, MonthAttestation.grade, MonthAttestation.month FROM MonthAttestation, Discipline, Students WHERE MonthAttestation.idDiscipline = Discipline.idDiscipline AND Students.idStudent = MonthAttestation.idStudent", null)
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

                var titleMonth: String = cursor.getString(2).toString() + " "

                titleMonthName.text = "Аттестация за $titleMonth"

                row.addView(textDis, layoutParams)
                row.addView(textGrade, layoutParams)
                tableRet.addView(row)
            } while (cursor.moveToNext())
        } else Log.d("mLog", "0 rows")
        cursor.close()

        return v
    }
}