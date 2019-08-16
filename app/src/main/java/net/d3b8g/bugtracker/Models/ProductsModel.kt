package net.d3b8g.bugtracker.Models

class ProductsModel(
        val name:String,
        var avatar:String,
        val latestVersion:String?,
        val amountReports:Int,
        val my_amountReports:Int,
        val amountWishes:Int
)