package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.database.MenuDatabase
import com.example.littlelemon.database.MenuItem
import com.example.littlelemon.network.MenuItemNetwork
import com.example.littlelemon.network.MenuNetworkdata
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, MenuDatabase::class.java, "menu").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                val menuItems by database.menuDao().getAllMenuItems().observeAsState(emptyList())
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavigation(sharedPreferences, menuItems, this)
                }
            }
        }

        lifecycleScope.launch {
            withContext(IO) {
                val menuItems = getMenus()
                if (database.menuDao().isMenuEmpty()) {
                    saveMenuToDatabase(menuItems)
                }
            }
        }

    }

    private suspend fun getMenus(): List<MenuItemNetwork> {
        return client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetworkdata>()
            .menu
    }

    private fun saveMenuToDatabase(menu: List<MenuItemNetwork>) {
        val menuItems = menu.map { it.toItemMenuRoom() }
        database.menuDao().saveMenuItem(*menuItems.toTypedArray())
    }
}

@Composable
fun MyNavigation(sharedPreferences: SharedPreferences?, menuItems: List<MenuItem>, context: Context) {
    val navController = rememberNavController()

    Navigation(
        sharedPreferences = sharedPreferences!!,
        navController = navController,
        menuItems = menuItems,
        context=context
    )

}



