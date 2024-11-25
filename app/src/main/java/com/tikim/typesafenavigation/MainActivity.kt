package com.tikim.typesafenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tikim.typesafenavigation.ui.theme.TypeSafeNavigationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TypeSafeNavigationTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.Home
                ) {
                    composable<Route.Home> {
                        HomeScreen(
                            onClick = {
                                navController.navigate(
                                    Route.Detail(
                                        id = "0",
                                        name = "사과",
                                        description = "비타민, 식이 섬유 등 다양한 영양소가 있습니다."
                                    )
                                )
                            }
                        )
                    }
                    composable<Route.Detail> {
                        val args = it.toRoute<Route.Detail>()

                        val id = args.id
                        val name = args.name
                        val description = args.description

                        DetailScreen(
                            id = id,
                            name = name,
                            description = description,
                            onClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Home"
                )
                Button(
                    onClick = onClick
                ) {
                    Text(
                        text = "Go to Detail Screen"
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    id: String,
    name: String,
    description: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "id : $id"
            )
            Text(
                text = "name : $name"
            )
            Text(
                text = description
            )
            Button(
                onClick = onClick
            ) {
                Text(
                    text = "Back"
                )
            }
        }
    }
}