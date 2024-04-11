package com.example.guesstheflag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheflag.ui.theme.GuessTheFlagTheme

//Video link
//https://drive.google.com/drive/folders/19mBeAENgOOlSKCEay46ec0W-_z3OSIxu?usp=sharing

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           display()
        }
    }
}
@Composable
fun display() {
    var checked by rememberSaveable { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        Row {


            Text(text = "Timer  ", fontSize = 18.sp, modifier = Modifier.padding(top = 10.dp))
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it


                }
            )
        }
        Row {
            val context = LocalContext.current
            Button(onClick ={
                var i = Intent(context, MainActivity2::class.java)
                i.putExtra("checked", checked)
                context.startActivity(i)


            },modifier = Modifier.width(170.dp) ) {
                Text(text = " Guess the Country")
            }
        }
        Row {
            val context = LocalContext.current
            Button(onClick ={
                var i = Intent(context, MainActivity3::class.java)
                i.putExtra("checked", checked)
                context.startActivity(i)

            },modifier = Modifier.width(170.dp)) {
                Text(text = "Guess-Hints")
            }
        }
        Row {
            val context = LocalContext.current
            Button(onClick ={
                var i = Intent(context, MainActivity4::class.java)
                i.putExtra("checked", checked)
                context.startActivity(i)

            },modifier = Modifier.width(170.dp)) {
                Text(text = "Guess the Flag")
            }
        }
        Row {
            val context = LocalContext.current
            Button(onClick ={
                var i = Intent(context, MainActivity5::class.java)
                i.putExtra("checked", checked)
                context.startActivity(i)

            },modifier = Modifier.width(170.dp)) {
                Text(text = "Advanced Level")
            }
        }

        Row {
            val context = LocalContext.current
            Button(onClick ={
                var i = Intent(context, MainActivity6::class.java)

                context.startActivity(i)

            },modifier = Modifier.width(170.dp)) {
                Text(text = "Instructions")
            }
        }

    }


}