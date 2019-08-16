package net.d3b8g.bugtracker.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import net.d3b8g.bugtracker.Constants.GlobalConstants.Companion.is_authorized
import net.d3b8g.bugtracker.R

class HelloFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val inflate = inflater.inflate(R.layout.fragment_hello,container,false)

        val avatar = inflate.findViewById<CircleImageView>(R.id.fh_avatar)

        if(is_authorized){
            Log.e("RRR","auth true")
        }

        return inflate
    }
}