package com.example.guesstheflag


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity6 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            instructions()
        }
    }
}

@Composable
fun instructions(){
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

        Row {
            Card(modifier = Modifier.background(Color.Gray).padding(top = 10.dp)) {
                Text(text = "1. Guess the Country" ,modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center, fontSize = 22.sp)
                Text(text = "1. Click \"Guess the Country\" to start.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center ,fontSize = 18.sp)
                Text(text = "2. See a random flag.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "3. Select the country from the list.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "4. Hit \"Submit\" to check your guess.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "5. Get instant feedback: \"CORRECT!\" in green or \"WRONG!\" in red with the correct answer in blue..",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "6. Click \"Next\" for a new flag.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "7. Repeat and enjoy!",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
            }

        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        )  {
            Card(modifier = Modifier.background(Color.Gray).padding(top = 10.dp)) {
                Text(text = "2.Guess-Hints" ,modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center, fontSize = 22.sp)
                Text(text = "1. Click \"Guess-Hints\" to start.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center ,fontSize = 18.sp)
                Text(text = "2. See a random flag.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "3. Guess the country's name letter by letter.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "4. Type a single letter in the textbox.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "5. Click \"Submit\" to check your guess.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text =  "6. If correct, the letter will replace corresponding dashes.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text =  "7. If wrong, the dashes stay the same.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text =  "8. Keep guessing until you solve the puzzle.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
            }

        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        )  {
            Card(modifier = Modifier.background(Color.Gray).padding(top = 10.dp)) {
                Text(text = "3.Guess the Flag" ,modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center, fontSize = 22.sp)
                Text(text = "1. Click \"Guess the Flag\" to start.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center ,fontSize = 18.sp)
                Text(text = "2. See a random flag.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "3. Click the flag you think corresponds to the displayed country name.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "4. You only get one chance.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "5. If correct, you'll see \"CORRECT!\" in green; if wrong, \"WRONG!\" in red.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text =  "6. Click \"Next\" to play again with new flags.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)

            }

        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        )  {
            Card(modifier = Modifier.background(Color.Gray).padding(top = 10.dp)) {
                Text(text = "3.Advanced Level" ,modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center, fontSize = 22.sp)
                Text(text = "1. Click \"Advanced Level\" to start.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center ,fontSize = 18.sp)
                Text(text = "2. See three random flags and three textboxes.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "3. Type the corresponding country name into each textbox.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "4. Click \"Submit\" to check your answers.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "5. Correct answers turn green and become uneditable.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text =  "6. Incorrect answers stay red and editable.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text =  "7. After three incorrect attempts, see correct answers in blue.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text =  "8. Click \"Next\" to move to the next set of flags.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text = "9. Earn one point for each correct guess.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
                Text(text ="10. Keep track of your score displayed on the top right.",modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,fontSize = 18.sp)
            }

        }


    }
}