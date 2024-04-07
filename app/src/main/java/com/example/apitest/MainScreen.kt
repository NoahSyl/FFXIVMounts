package com.example.apitest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainScreen(viewModel: MyViewModel, navController: NavController) {
    // Estado para almacenar la selección de la pestaña de la barra inferior (por defecto se queda la lista -> ListScreen)
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }

    // Lista de elementos de la API
    val data by viewModel.mounts.observeAsState() //coger los datos desde la ViewModel
    val mounts = data?.results ?: emptyList() //asegurar integridad

    Scaffold( //Scaffold principal para la lista
        bottomBar = { //Barra inferior
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* acción */ }) {
                        Icon(Icons.Filled.List, contentDescription = "Lista")
                    }
                    IconButton(onClick = { /* acción */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "Monturas obtenidas")
                    }
                    IconButton(onClick = { /* acción */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Monturas deseadas")
                    }
                },
                floatingActionButton = { //botón flotante a la derecha -> sin implementar acciones aún
                    FloatingActionButton(
                        onClick = { /* acción */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.elevation()
                    ) {
                        Icon(Icons.Filled.Add, " ")
                    }
                }
            )
        },
    ) { innerPadding -> //padding para respetar el tamaño del scaffold
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (selectedTab) { //Control para elegir la pestaña seleccionada
                0 -> { //0 es la lista
                    ListScreen(viewModel = viewModel, navController = navController)
                }
                // ¿Agregar más direcciones?
            }
        }
    }
}