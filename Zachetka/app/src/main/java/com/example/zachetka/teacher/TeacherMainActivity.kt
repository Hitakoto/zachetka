package com.example.zachetka.teacher

import android.app.Dialog
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.zachetka.R
import com.example.zachetka.databinding.ActivityTeacherMainBinding
import com.example.zachetka.dbHelper.DBHelper
import com.google.android.material.navigation.NavigationView
import java.io.IOException

class TeacherMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTeacherMainBinding

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    private lateinit var quantityLate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeacherMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarTeacherMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navViewT
        val navController = findNavController(R.id.nav_host_fragment_content_teacher_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home_teacher, R.id.nav_month_teacher, R.id.nav_semestr_teacher, R.id.nav_settings_teacher
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.fragment_month_student)

        quantityLate = findViewById(R.id.quantityLateT)

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

        //Для вывода данных о курирующей группе
        //SELECT Groups.title FROM Groups, Teachers, Users WHERE Groups.idTeacher = Teachers.idTeacher AND Teachers.idUser = Users.idUser AND Users.idUser = $id

        /*val id : String? = intent.getStringExtra("iduser")

        println("auishdiuaefuiaelfewuafuiyeuaiyfiuaeyfiuyueifueyfiuaeiufyiueayfiueayufiaeusfuyegsafuiguyeagfuieagfyaegsfukgaeyugfiueagfyuagefukyuasgfuaesgfyusgaefuigauyefguaesgfuyesgafugsaeyufgiuleasgfykegafluiagfuaegsifeue "+id)

        val sql = "SELECT lateHours from RecordBook WHERE RecordBook.student = $id"
        val cursor: Cursor = database.rawQuery(sql, null)
        val recordBook: HashMap<String, Any> = HashMap<String, Any>()
        cursor.moveToFirst ()
        while (!cursor.isAfterLast) {
            recordBook["LateHours"] = cursor.getString(0)
            cursor.moveToNext()
        }
        quantityLate.text = recordBook["LateHours"].toString()*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_teacher_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}