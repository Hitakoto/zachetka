package com.example.zachetka.admin.adminMainFragments.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.zachetka.R
import com.example.zachetka.SignInActivity

class SettingsAdminFragment : Fragment() {

    lateinit var v : View
    lateinit var btnSignOut : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_settings_admin, container, false)

        btnSignOut = v.findViewById(R.id.tvbSignOutA)

        btnSignOut.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(activity, SignInActivity::class.java))
            Toast.makeText(activity, "Выход", Toast.LENGTH_LONG).show()
        })

        return v
    }
}