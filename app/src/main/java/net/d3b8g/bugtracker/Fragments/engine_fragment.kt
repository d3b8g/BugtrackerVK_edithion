package net.d3b8g.bugtracker.Fragments

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import net.d3b8g.bugtracker.MainActivity


fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}
fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun isShowPopup(context:Context):Boolean{
    return Dialog(context).isShowing
}
fun restartApp(context: Context){
    val mStartActivity = Intent(context, MainActivity::class.java)
    val mPendingIntentId = 123456
    val mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT)
    val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
    System.exit(0)
}
