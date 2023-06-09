package com.example.zachetka.student.studentMainFragments.home

import android.content.Intent
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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.zachetka.R
import com.example.zachetka.admin.AdminMainActivity
import com.example.zachetka.student.StudentRecordActivity
import com.example.zachetka.dbHelper.DBHelper
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

class HomeStudentFragment : Fragment() {

    lateinit var ivbRecord : ImageView
    lateinit var v : View

    lateinit var linRet: LinearLayout
    lateinit var tableRet: TableLayout

    private lateinit var titleMonthName: TextView
    private lateinit var late: TextView
    lateinit var noneInfo: TextView

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home_student, container, false)

        ivbRecord = v.findViewById(R.id.ivb_recS)

        titleMonthName = v.findViewById(R.id.titleGradesS)
        late = v.findViewById(R.id.quantityLate)
        noneInfo = v.findViewById(R.id.noneInfoS)

        val id : String? = activity?.intent?.getStringExtra("idUser")

        ivbRecord.setOnClickListener(View.OnClickListener { _ ->
            var intentSRA = Intent(activity, StudentRecordActivity::class.java)
            intentSRA.putExtra("idUser", id.toString());
            startActivity(intentSRA)
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

        val currentDate = LocalDate.now()
        val month = currentDate.month

        val monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
        val monthNameInGenitiveCase = monthName.take(monthName.length - 1) + "ь"

        val formatter = DateTimeFormatter.ofPattern("LLLL", Locale("ru"))
        val capitalizedMonthNameInGenitiveCase = monthNameInGenitiveCase.replaceFirstChar { it.uppercase() }
        val formattedMonthName = capitalizedMonthNameInGenitiveCase.format(formatter)

        val cursor = database.rawQuery("SELECT Discipline.nameDis, MonthAttestation.grade, MonthAttestation.month, MonthAttestation.colLateHY, MonthAttestation.colLateHN FROM MonthAttestation, Discipline, Students WHERE MonthAttestation.idDiscipline = Discipline.idDiscipline AND Students.idStudent = MonthAttestation.idStudent AND Students.idUser = $id AND MonthAttestation.month = '$formattedMonthName'", null)
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

                var lateY = 0
                lateY += cursor.getInt(3)

                var lateNY = 0
                lateNY += cursor.getInt(4)

                titleMonthName.text = "Аттестация за $formattedMonthName"
                late.text = "ув: $lateY, неув: $lateNY"

                row.addView(textDis, layoutParams)
                row.addView(textGrade, layoutParams)
                tableRet.addView(row)
            } while (cursor.moveToNext())
        } else {
            titleMonthName.text = "Аттестация за $formattedMonthName"
            tableRet.visibility = View.GONE
            noneInfo.visibility = View.VISIBLE
            noneInfo.text = "Нет данных по аттестации"
        }
        cursor.close()

        return v
    }
}