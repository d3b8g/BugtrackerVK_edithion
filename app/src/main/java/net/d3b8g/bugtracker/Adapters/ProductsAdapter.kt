package net.d3b8g.bugtracker.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import net.d3b8g.bugtracker.Models.ProductsModel
import net.d3b8g.bugtracker.R
import org.w3c.dom.Text

class ProductsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var productList: ArrayList<ProductsModel> = ArrayList()

    fun updateList(list:ArrayList<ProductsModel>){
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val itemView = layoutInflater.inflate(R.layout.cell_product,p0,false)
        return ProductsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if(p0 is ProductsViewHolder) p0.bind(productList[p1])
    }

    class ProductsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val avatar = itemView.findViewById<ImageView>(R.id.product_avatar)
        val pname = itemView.findViewById<TextView>(R.id.product_name)
        val latestVersion = itemView.findViewById<TextView>(R.id.product_latest_version)
        val all_reports = itemView.findViewById<TextView>(R.id.product_all_reports)
        val my_report_count = itemView.findViewById<TextView>(R.id.product_my_reports)
        val report_word = itemView.findViewById<TextView>(R.id.product_my_report_word)

        fun bind(item:ProductsModel){

        }
    }
}