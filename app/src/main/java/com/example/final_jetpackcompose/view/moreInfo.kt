package com.example.final_jetpackcompose.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cityInfoScreen(navigationController: NavHostController, citySelec : String) {
    var listaImg by remember { mutableStateOf<List<String>>(emptyList()) }
    var listText  by remember { mutableStateOf<List<String>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        listaImg = downloadImg(citySelec)
        listText = downloadText(citySelec)
        loading = false
    }

    if (loading) {
        // Mientras se cargan las imágenes, muestra un indicador de progreso.
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        // TopBar de la pantalla
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray),
            title = {
                Text(
                    text = citySelec,
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigationController.navigateUp()
                    }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
        )

        // LazyColumn para el contenido desplazable
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            item {
                // Contenido de la pantalla
                ImageCarousel(listaImg)

                Spacer(modifier = Modifier.height(16.dp))
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = listText[0],
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))

                // Función textGenerator con margen lateral
                Text(
                    text = listText[1],
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))

                // Función textGenerator con margen lateral
                Text(
                    text = listText[2],
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(urlList: List<String>) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        urlList.count()
    }

    HorizontalPager(state = pagerState) { page ->
        AsyncImage(
            model = urlList[page],
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .width(IntrinsicSize.Max)
                .height(200.dp)
        )
    }
}

//Función para descargar las imagenes
suspend fun downloadImg(citySelec : String): List<String> = suspendCoroutine { continuation ->
    val database = Firebase.database
    val ref = database.getReference("citys")
    val cityRef = ref.child(citySelec)
    var listaImg: List<String> = emptyList()

    cityRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val value1 = snapshot.child("0").getValue().toString()
            val value2 = snapshot.child("1").getValue().toString()
            val value3 = snapshot.child("2").getValue().toString()
            listaImg = listOf(value1, value2, value3)
            //Log.w("P","Values: " + value1 + value2 + value3)


            // Cuando la descarga se completa, se llama a la continuación con la lista de imágenes.
            continuation.resume(listaImg)
        }

        override fun onCancelled(error: DatabaseError) {}
    })
}

//Función para descargar los textos
suspend fun downloadText(citySelec : String): List<String> = suspendCoroutine { continuation ->
    val database = Firebase.database
    val ref = database.getReference("Texts")
    val cityRef = ref.child(citySelec)
    var listaImg: List<String> = emptyList()

    cityRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val value1 = snapshot.child("0").getValue().toString()
            val value2 = snapshot.child("1").getValue().toString()
            val value3 = snapshot.child("2").getValue().toString()
            listaImg = listOf(value1, value2, value3)
            Log.w("P","Values: " + value1 + value2 + value3)

            // Cuando la descarga se completa, se llama a la continuación con la lista de imágenes.
            continuation.resume(listaImg)
        }

        override fun onCancelled(error: DatabaseError) {}
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewMyScreenContent() {
    //MyScreenContent()
}
