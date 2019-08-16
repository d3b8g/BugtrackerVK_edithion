package net.d3b8g.bugtracker

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import net.d3b8g.bugtracker.Constants.WorkerConstants.Companion.delay_first_start
import net.d3b8g.bugtracker.Fragments.HelloFragment
import net.d3b8g.bugtracker.Fragments.replaceFragment
import net.d3b8g.bugtracker.SupportClass.EditionBottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navigator_panel:EditionBottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator_panel = findViewById(R.id.navigator)
        navigator_panel.selectedItemId = R.id.nav_reports
        navigator_panel.inflateMenu(R.menu.nav_menu)
        navigator_panel.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.nav_reports->{}
                R.id.nav_create->{}
                R.id.nav_products->{}
            }
            true
        }

        StartAppWrong()
    }

    fun StartAppWrong(){
        val fragmentStart = HelloFragment()
        replaceFragment(fragmentStart,R.id.frame_first_start)
    }
}
