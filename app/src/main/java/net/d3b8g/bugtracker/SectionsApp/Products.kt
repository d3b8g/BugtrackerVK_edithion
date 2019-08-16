package net.d3b8g.bugtracker.SectionsApp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.d3b8g.bugtracker.R

class Products:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val inflate = inflater.inflate(R.layout.products,container,false)

        return inflate
    }
}