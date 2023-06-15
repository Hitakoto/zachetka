package com.example.zachetka.student

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
import com.example.zachetka.databinding.ActivityStudentMainBinding
import com.example.zachetka.dbHelper.DBHelper
import com.google.android.material.navigation.NavigationView
import java.io.IOException

class StudentMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityStudentMainBinding

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navViewS
        val navController = findNavController(R.id.nav_host_fragment_content_student_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home_student, R.id.nav_month_student, R.id.nav_semestr_student, R.id.nav_settings_student
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.fragment_month_student)

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

        val id : String? = intent.getStringExtra("idUser")

        println("auishdiuaefuiaelfewuafuiyeuaiyfiuaeyfiuyueifueyfiuaeiufyiueayfiueayufiaeusfuyegsafuiguyeagfuieagfyaegsfukgaeyugfiueagfyuagefukyuasgfuaesgfyusgaefuigauyefguaesgfuyesgafugsaeyufgiuleasgfykegafluiagfuaegsifeue "+id)

        /*val sql = "SELECT lateHours from RecordBook WHERE RecordBook.student = $id"
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
        val navController = findNavController(R.id.nav_host_fragment_content_student_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}