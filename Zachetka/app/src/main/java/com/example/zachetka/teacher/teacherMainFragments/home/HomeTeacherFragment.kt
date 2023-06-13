package com.example.zachetka.teacher.teacherMainFragments.home

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
import com.example.zachetka.dbHelper.DBHelper
import com.example.zachetka.teacher.TeacherRecordActivity
import java.io.IOException
import android.widget.AdapterView
import android.widget.ArrayAdapter


class HomeTeacherFragment : Fragment() {

    lateinit var ivbRecord : ImageView
    lateinit var v : View

    lateinit var linRet: LinearLayout
    lateinit var tableRet: TableLayout

    lateinit var titleMonthName: TextView

    lateinit var spinnerStudentsMain: Spinner
    lateinit var spinnerGroupsMain: Spinner
    var selectedAttribute: String? = null

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home_teacher, container, false)

        ivbRecord = v.findViewById(R.id.ivb_recT)

        titleMonthName = v.findViewById(R.id.titleUsersT)

        spinnerStudentsMain = v.findViewById(R.id.spinnerStudentsMain)
        spinnerGroupsMain = v.findViewById(R.id.spinnerGroupsMain)

        val id : String? = activity?.intent?.getStringExtra("idUser")

        //val customTypeface = Typeface.createFromAsset(activity?.assets, "font/rubik_bold.ttf")

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
        val adapterUsers1: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, users1)
        adapterUsers1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStudentsMain.adapter = adapterUsers1
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
        val adapterGroups1: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, groups1)
        adapterGroups1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGroupsMain.adapter = adapterGroups1
        cursorGroups1.close()

        var studentName: String = spinnerStudentsMain.selectedItem.toString()
        var groupsName: String = spinnerGroupsMain.selectedItem.toString()

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
        val adapterUsers: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, users)
        adapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStudentsMain.adapter = adapterUsers
        cursorUsers.close()

        val layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.weight = 1f

        spinnerStudentsMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                if (selectedAttribute != selectedItem) {
                    // Выбран новый атрибут, сбрасываем данные прошлого атрибута
                    tableRet.removeAllViews()
                    val rowTitle = TableRow(activity!!)
                    val disciplineT = TextView(activity!!)
                    disciplineT.text = "Дисциплина"
                    disciplineT.setTextColor(Color.rgb(24, 79, 154))
                    disciplineT.gravity = Gravity.CENTER
                    disciplineT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    disciplineT.textSize = 16f
                    disciplineT.setPadding(3,3,3,3,)
                    disciplineT.setTypeface(null, Typeface.BOLD)
                    //disciplineT.typeface = customTypeface

                    val gradeT = TextView(activity!!)
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
                    tableRet.addView(rowTitle)

                    selectedAttribute = selectedItem
                }

                studentName = spinnerStudentsMain.selectedItem.toString()
                groupsName = spinnerGroupsMain.selectedItem.toString()

                val cursor = database.rawQuery("SELECT Discipline.nameDis, MonthAttestation.grade, MonthAttestation.month FROM MonthAttestation, Discipline, Students, Users, Groups WHERE MonthAttestation.idDiscipline = Discipline.idDiscipline AND Students.idStudent = MonthAttestation.idStudent AND Students.idUser = Users.idUser AND Users.surname = '" + studentName.substring(0, studentName.length - 5) + "' AND Groups.idGroup = Students.idGroup AND Groups.title = '"+ groupsName.substring(0, groupsName.length - 1) + "' ORDER BY Discipline.nameDis", null)
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
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        spinnerGroupsMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                studentName = spinnerStudentsMain.selectedItem.toString()
                groupsName = spinnerGroupsMain.selectedItem.toString()

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
                val adapterUsers: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, users)
                adapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerStudentsMain.adapter = adapterUsers
                cursorUsers.close()

                if (selectedAttribute != selectedItem) {
                    // Выбран новый атрибут, сбрасываем данные прошлого атрибута
                    tableRet.removeAllViews()
                    val rowTitle = TableRow(activity!!)
                    val disciplineT = TextView(activity!!)
                    disciplineT.text = "Дисциплина"
                    disciplineT.setTextColor(Color.rgb(24, 79, 154))
                    disciplineT.gravity = Gravity.CENTER
                    disciplineT.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    disciplineT.textSize = 16f
                    disciplineT.setPadding(3,3,3,3,)
                    disciplineT.setTypeface(null, Typeface.BOLD)
                    //disciplineT.typeface = customTypeface

                    val gradeT = TextView(activity!!)
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
                    tableRet.addView(rowTitle)

                    selectedAttribute = selectedItem
                }

                val cursor = database.rawQuery("SELECT Discipline.nameDis, MonthAttestation.grade, MonthAttestation.month FROM MonthAttestation, Discipline, Students, Users, Groups WHERE MonthAttestation.idDiscipline = Discipline.idDiscipline AND Students.idStudent = MonthAttestation.idStudent AND Students.idUser = Users.idUser AND Users.surname = '" + studentName.substring(0, studentName.length - 5) + "' AND Groups.idGroup = Students.idGroup AND Groups.title = '"+ groupsName.substring(0, groupsName.length - 1) +"' ORDER BY Discipline.nameDis", null)
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
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, когда ничего не выбрано
            }
        }

        return v
    }
}