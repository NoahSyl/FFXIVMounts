package com.example.apitest


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.apitest.model.Mounts
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, mount: Mounts?, myViewModel: MyViewModel) {


    Scaffold( //Añadimos un Scaffold para la pantalla de detalles
        topBar = {
            TopAppBar(
                title = { //Título superior -> en inglés porque es fancy
                    Text(text = "Mounts details")
                },
                navigationIcon = { //icono donde meteremos la navegación
                    IconButton(onClick = { navController.navigateUp() }) { //el botón nos devuelve a la pantalla anterior
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (mount != null) { //si la montura existe nos da la información

                /*IMAGEN*/

                Image(
                    painter = rememberAsyncImagePainter(model = mount.image),
                    contentDescription = mount.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(100.dp)
                        .size(300.dp) // Si no es enana
                )

                /*CAMPOS DE TEXTO*/

                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = mount.name,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                    Text(
                        text = mount.description,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                    Text(
                        text = mount.tooltip,
                        modifier = Modifier.padding(bottom = 30.dp)
                    )
                }
            }
        }
    }
}
