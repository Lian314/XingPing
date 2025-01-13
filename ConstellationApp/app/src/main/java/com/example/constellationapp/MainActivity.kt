package com.example.constellationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.constellationapp.ui.theme.ConstellationAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material.icons.filled.StarBorder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstellationAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF0F8FF)) // 浅蓝色背景
        ) {
            NavHost(navController = navController, startDestination = "login") {
                composable("moon") { MoonScreen() }
                composable("star") { StarScreen(navController) }
                composable("sun") { SunScreen() }
                composable("login") { LoginScreen(navController) }
                composable("register") { RegisterScreen(navController) }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem("Moon", Icons.Default.WbTwilight),
        NavigationItem("Star", Icons.Default.StarBorder),
        NavigationItem("Sun", Icons.Default.CalendarToday)
    )

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                selectedContentColor = Color(0xFF4A90E2), // 深蓝色
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = false,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

data class NavigationItem(val title: String, val icon: ImageVector, val route: String = title.lowercase())

@Composable
fun ConstellationAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = Color(0xFF4A90E2), // 深蓝色
            background = Color(0xFFF0F8FF) // 浅蓝色背景
        ),
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}