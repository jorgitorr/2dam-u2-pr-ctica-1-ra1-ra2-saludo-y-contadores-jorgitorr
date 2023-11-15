package com.jorgearceruiz97.a2dam_u2_pr_ctica_1_ra1_ra2_saludo_y_contadores_jorgitorr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.window.DialogProperties
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
                    pantallaBoton()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun pantallaBoton(){
    var nombre by rememberSaveable { mutableStateOf("") }
    var contAceptar by rememberSaveable { mutableStateOf(0) }
    var contCancelar by rememberSaveable { mutableStateOf(0) }
    var showDialogo by rememberSaveable { mutableStateOf(false) }


    if(showDialogo){
        dialogo(
            onDissmissRequest = {showDialogo = false} ,
            onAccept = {contAceptar++},
            accValue = {contAceptar.toString()},
            onCancel = {contCancelar++},
            canValue = {contCancelar.toString()},
            onTextChange = {it : String -> nombre = it},
            nameValue = {nombre}
        )
    }


    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(modifier = Modifier.padding(bottom = 10.dp), onClick = {
            showDialogo = true
        } ) {
            Text(text = "Saludar")
        }
        Text(text = "Saludos $nombre",
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
//@Preview(showBackground = true)
@Composable
fun dialogo(onDissmissRequest: () -> Unit,//está funcion se llama cuando el usuario da fuera de la ventana
            onAccept: () -> Unit,
            accValue: () -> String,
            onCancel: () -> Unit,
            canValue: () -> String,
            onTextChange: (String) -> Unit,
            nameValue: () -> String){
    Column(
        Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(top = 15.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Dialog(onDismissRequest = { onDissmissRequest() },
            properties = DialogProperties(dismissOnBackPress = true)
        ) {
            Row(){
                Text(text = "Configuracion",
                    Modifier.padding(bottom = 2.dp, start = 250.dp), fontSize = 20.sp)
            }

            Row(Modifier.fillMaxWidth()){
                OutlinedTextField(
                    value = nameValue(),
                    onValueChange = { onTextChange(it) },
                    label = { Text(text = "Introduce tu nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        textColor = Color.White
                    )
                )
            }

            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    onAccept()
                    onDissmissRequest()//sale
                }) {
                    Text(text = "Aceptar ${accValue()}")
                }
                Button(onClick = { onTextChange("") }, enabled = nameValue().isNotEmpty()) {
                    Text(text = "Limpiar")
                }
                Button(onClick = {
                    onCancel()
                    onDissmissRequest()//sale
                    onTextChange("")

                }) {
                    Text(text = "Cancelar ${canValue()}")
                }
            }
        }



    }


}
