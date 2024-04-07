package com.example.FFXIVMounts

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.FFXIVMounts.model.Mounts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MountItem(mount: Mounts, onMountSelected: (String) -> Unit) {

    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { onMountSelected(mount.name) }) { //al clicar navegamos a la pantalla de datos
        Row (

            /*IMAGEN*/

        ){
            Image(
                painter = rememberAsyncImagePainter(model = mount.icon),
                contentDescription = mount.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(1.dp)
                    .size(55.dp)
            )

            /*CAMPOS DE TEXTO*/

            Column {
                Text(
                    text = mount.name,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
              //  Text(text = mount.tooltip)

            }
        }
    }
}

@Composable
fun ListScreen(viewModel: MyViewModel, navController: NavController) {

    val data by viewModel.mounts.observeAsState() //recibe los datos de cada item desde la ViewModel
    val mounts = data?.results ?: emptyList() //aseguramos la integridad
    LazyVerticalGrid(columns = GridCells.Fixed(2)) { //creamos la lista
        items(mounts) { mount ->
            MountItem(mount = mount) { mountName ->
                navController.navigate("${Routes.DetailScreen.route}/$mountName") //navegación para cada item
            }
        }
    }
}

