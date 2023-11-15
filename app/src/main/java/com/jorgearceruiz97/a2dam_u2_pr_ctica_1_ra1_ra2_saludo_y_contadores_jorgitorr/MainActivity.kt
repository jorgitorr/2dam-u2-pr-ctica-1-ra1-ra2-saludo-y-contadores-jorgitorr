package com.jorgearceruiz97.a2dam_u2_pr_ctica_1_ra1_ra2_saludo_y_contadores_jorgitorr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jorgearceruiz97.a2dam_u2_pr_ctica_1_ra1_ra2_saludo_y_contadores_jorgitorr.ui.theme._2damu2prctica1ra1ra2saludoycontadoresjorgitorrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2damu2prctica1ra1ra2saludoycontadoresjorgitorrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    dialogo()
                }
            }
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun pantallaBoton(nombre: String, cont:Int){
    val texto:String = ""

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(modifier = Modifier.padding(bottom = 10.dp), onClick = {

        } ) {
            Text(text = "Saludar")
        }
        Text(text = texto,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .border(2.dp, Color.Black)
                .height(50.dp)
                .width(250.dp)
                .wrapContentHeight(Alignment.CenterVertically))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun dialogo(){
    var contAceptar by rememberSaveable { mutableStateOf(0) }
    var contCancelar by rememberSaveable { mutableStateOf(0) }
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(top = 15.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Configuracion",
            Modifier.padding(bottom = 2.dp, start = 250.dp), fontSize = 20.sp)

        OutlinedTextField(
            value = text,
            onValueChange = { text = it},
            label = { Text(text = "Introduce tu nombre")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                textColor = Color.Black))

        Row (
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = { contAceptar++ }) {
                Text(text = "Aceptar")
            }
            Button(onClick = { text = "" }, enabled = text.isNotEmpty()) {
                Text(text = "Limpiar")
            }
            Button(onClick = { contCancelar++ }) {
                Text(text = "Cancelar")
            }
        }

    }

}