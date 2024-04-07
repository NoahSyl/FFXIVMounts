package com.example.FFXIVMounts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.FFXIVMounts.ui.theme.APITestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myViewModel by viewModels<MyViewModel>()
        myViewModel.getMounts() //Cargamos las monturas (!!!)


        setContent {
            APITestTheme {
                val navigationController =
                    rememberNavController() // Declarar NavController antes de su uso

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.MainScreen.route //comienza en la pantalla principal que carga la ListScreen -> Ojo con duplicar la llamada
                    ) {

                        composable(Routes.ListScreen.route) {
                            ListScreen(
                                viewModel = myViewModel,
                                navController = navigationController
                            )
                        }
                        composable(Routes.MainScreen.route) {
                            MainScreen(
                                viewModel = myViewModel,
                                navController = navigationController
                            )
                        }

                        //En la pantalla de detalles hay que cargar los datos de las monturas

                        composable(Routes.DetailScreen.route + "/{mountName}") { backStackEntry ->
                            val mountName = backStackEntry.arguments?.getString("mountName") ?: ""
                            val mount =
                                myViewModel.mounts.value?.results?.find { it.name == mountName }
                            DetailScreen(
                                navController = navigationController,
                                mount = mount, //extraemos la información de la ViewModel
                                myViewModel = myViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}