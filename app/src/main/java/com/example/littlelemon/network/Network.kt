package com.example.littlelemon.network

import com.example.littlelemon.database.MenuItem
import kotlinx.serialization.Serializable
@Serializable
data class MenuNetworkdata(val menu: List<MenuItemNetwork>)

@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
){
    fun toItemMenuRoom() = MenuItem(id,title,description,price,image,category)
}