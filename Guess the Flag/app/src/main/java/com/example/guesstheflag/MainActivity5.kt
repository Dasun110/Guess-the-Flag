package com.example.guesstheflag


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val checked = intent.getBooleanExtra("checked", true)
            PreviewRandomImage4(checked)
        }
    }
}

@Composable
fun AdvancedLevel(imageList: List<Int>,countryList: List<String>,checked: Boolean){

    var newChecked by rememberSaveable { mutableStateOf(checked) }

    // Generate a random index
    var randomIndex1 by rememberSaveable { mutableStateOf(Random.nextInt(0, imageList.size)) }
    var randomIndex2 by rememberSaveable { mutableStateOf(Random.nextInt(0, imageList.size)) }
    var randomIndex3 by rememberSaveable { mutableStateOf(Random.nextInt(0, imageList.size)) }

    while (randomIndex1 == randomIndex2 || randomIndex2 == randomIndex3 || randomIndex1 == randomIndex3){
        randomIndex1= Random.nextInt(0, imageList.size)
        randomIndex2= Random.nextInt(0, imageList.size)
        randomIndex3= Random.nextInt(0, imageList.size)
    }
    // Get random image
    var imageResource1 = imageList[randomIndex1]
    var imageResource2 = imageList[randomIndex2]
    var imageResource3 = imageList[randomIndex3]
    // Get image name
    val index1 = imageList.indexOf(imageResource1)
    var imageName1 = countryList[index1]
    val index2 = imageList.indexOf(imageResource2)
    var imageName2 = countryList[index2]
    val index3 = imageList.indexOf(imageResource3)
    var imageName3 = countryList[index3]

    //  Crate userInputLatter variable for get user input data
    var userInputLatter1 by rememberSaveable { mutableStateOf("") }
    var userInputLatter2 by rememberSaveable { mutableStateOf("") }
    var userInputLatter3 by rememberSaveable { mutableStateOf("") }

    var Correct by rememberSaveable { mutableStateOf("") }
    var buttonText by rememberSaveable { mutableStateOf("Submit") }


    val isButtonEnabled1 = rememberSaveable { mutableStateOf(true) }
    val isButtonEnabled2 = rememberSaveable { mutableStateOf(true) }
    val isButtonEnabled3 = rememberSaveable { mutableStateOf(true) }


//  add default background and text colour
    var backgroundColour1 by remember { mutableStateOf(Color.White) }
    var textColour1 by remember { mutableStateOf(Color.Black) }
    var backgroundColour2 by remember { mutableStateOf(Color.White) }
    var textColour2 by remember { mutableStateOf(Color.Black) }
    var backgroundColour3 by remember { mutableStateOf(Color.White) }
    var textColour3 by remember{ mutableStateOf(Color.Black) }
//  Add all usre input in userInputList
    var userInputList = mutableListOf(userInputLatter1,userInputLatter2,userInputLatter3)
    //  Add all image names in allImageNameList
    var allImageNameList = mutableListOf(imageName1.lowercase(),imageName2.lowercase(),imageName3.lowercase())
    var inCorrectCount by rememberSaveable { mutableStateOf(0) }

    var allElementsExist by rememberSaveable { mutableStateOf(false) }
    var marks by rememberSaveable { mutableStateOf((0)) }

    var timeLeft by rememberSaveable { mutableStateOf(10) }


// Incorrect attempt  count greater than 3 change buttonText and   Correct="WRONG!"
    if (inCorrectCount>=3){
        buttonText="Next"
        Correct="WRONG!"
    }

    Log.e(imageName1,"image1")
    Log.e(imageName2,"image2")
    Log.e(imageName3,"image3")
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()){

//              Timer is end auto submit and display user is correct or wrong

            if (newChecked==true && buttonText=="Submit"){
                LaunchedEffect(key1 = timeLeft) {
                    while (timeLeft > 0) {
                        delay(1000L)
                        timeLeft--
                    }
                }
                if (timeLeft==0){
                    timeLeft=10
                    val allElements = allImageNameList.containsAll(userInputList)
                    if (buttonText=="Submit"){
                        marks=3
                        if (allElements){
                            Correct="CORRECT!"
                            buttonText="Next"

                            isButtonEnabled1.value=false
                            backgroundColour1= Color.Green
                            textColour1= Color.White

                            isButtonEnabled2.value=false
                            backgroundColour2= Color.Green
                            textColour2= Color.White

                            isButtonEnabled3.value=false
                            backgroundColour3= Color.Green
                            textColour3= Color.White
                        }else{
                            ++inCorrectCount
                            for (element in userInputList) {
                                if (allImageNameList.contains(element)) {
                                    if (element==imageName1.lowercase()){
                                        isButtonEnabled1.value=false
                                        backgroundColour1= Color.Green
                                        textColour1= Color.White

                                    }else if(element==imageName2.lowercase()){
                                        isButtonEnabled2.value=false
                                        backgroundColour2= Color.Green
                                        textColour2= Color.White

                                    }else{
                                        isButtonEnabled3.value=false
                                        backgroundColour3= Color.Green
                                        textColour3= Color.White

                                    }

                                }else if(userInputList.all { it.isEmpty() }) {
                                    backgroundColour1 = Color.Red
                                    textColour1 = Color.White
                                    backgroundColour2 = Color.Red
                                    textColour2 = Color.White
                                    backgroundColour3 = Color.Red
                                    textColour3 = Color.White
                                    marks=0
                                }else if(element==userInputLatter1 && backgroundColour1==Color.White){
                                    backgroundColour1= Color.Red
                                    textColour1= Color.White
                                    --marks

                                }else if (element==userInputLatter2 && backgroundColour2==Color.White) {
                                    backgroundColour2 = Color.Red
                                    textColour2 = Color.White
                                    --marks
                                }else  {
                                    Log.e(element,"element")
                                    Log.e(userInputLatter2,"userInputLatter2")
                                    Log.e(userInputLatter2,"userInputLatter2")
                                    backgroundColour3 = Color.Red
                                    textColour3 = Color.White
                                    --marks


                                }
                            }
                        }

                    }else{
                        userInputLatter1=""
                        userInputLatter2=""
                        userInputLatter3=""
                        Correct=""
                        buttonText="Submit"
                        isButtonEnabled1.value =true
                        isButtonEnabled2.value =true
                        isButtonEnabled3.value =true
                        backgroundColour1= Color.White
                        textColour1=Color.Black
                        backgroundColour2= Color.White
                        textColour2=Color.Black
                        backgroundColour3= Color.White
                        textColour3=Color.Black
                        userInputList.clear()
                        allImageNameList.clear()
                        inCorrectCount=0
                        marks=0


                        var newRandomIndex1 = Random.nextInt(0, imageList.size)
                        var newRandomIndex2 = Random.nextInt(0, imageList.size)
                        var newRandomIndex3 = Random.nextInt(0, imageList.size)
                        var randomIndexList= listOf(randomIndex1,randomIndex2,randomIndex3)
                        var newRandomList = listOf(newRandomIndex1,newRandomIndex2,newRandomIndex3)

                        for (element in newRandomList) {
                            if (randomIndexList.contains(element)) {
                                allElementsExist = true
                                break
                            }
                        }
                        if (allElementsExist) {
                            newRandomIndex1=Random.nextInt(0, imageList.size)
                            newRandomIndex2=Random.nextInt(0, imageList.size)
                            newRandomIndex3=Random.nextInt(0, imageList.size)
                            randomIndex1=newRandomIndex1
                            randomIndex2=newRandomIndex2
                            randomIndex3=newRandomIndex3
                        }
                        randomIndex1=newRandomIndex1
                        randomIndex2=newRandomIndex2
                        randomIndex3=newRandomIndex3

                    }

                }
                Text(text = "Time left: $timeLeft", fontSize = 24.sp, modifier = Modifier
                    .background(Color.Gray)
                    .padding(top = 10.dp))
            }

        }

        Row(horizontalArrangement = Arrangement.End,
//      Display user got marks
            modifier = Modifier.fillMaxWidth()) {
            Text(text= "score- $marks/3",
                textAlign = TextAlign.Right, fontSize = 24.sp,
                modifier = Modifier.background(color= Color.Gray)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Advanced Level",
                modifier = Modifier.padding(start = 50.dp, bottom = 10.dp ,top=10.dp),
                fontSize = 32.sp,

                )


        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
//            Log.e("img",imageResource.toString())
//            Display images
            Image(
                painter = painterResource(id = imageResource1),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .border(
                        BorderStroke(5.dp, Color.Black)

                    )
            )
            Image(
                painter = painterResource(id = imageResource2),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .border(
                        BorderStroke(5.dp, Color.Black)

                    )
            )
            Image(
                painter = painterResource(id = imageResource3),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)

                    .border(
                        BorderStroke(5.dp, Color.Black)

                    )
            )

        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)) {
            //  Crate outline text box for get user input

            OutlinedTextField(
                value = userInputLatter1,
                enabled = isButtonEnabled1.value,
                onValueChange = {userInput->
                    userInputLatter1 = userInput.lowercase()
                },
                label = { Text("Guess The 1st Image Country",style = TextStyle(color =textColour1, background = backgroundColour1)) }
            )
        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)) {
            OutlinedTextField(
                //  Crate outline text box for get user input

                value = userInputLatter2,
                enabled = isButtonEnabled2.value,
                onValueChange = {userInput->
                    userInputLatter2 = userInput.lowercase()
                },
                label = { Text("Guess The 2nd Image Country",style = TextStyle(color =textColour2, background = backgroundColour2)) }
            )
        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)) {
            OutlinedTextField(
                // Crate outline text box for get user input

                value = userInputLatter3,
                enabled = isButtonEnabled3.value,
                onValueChange = {userInput->
                    userInputLatter3 = userInput.lowercase()
                },
                label = { Text("Guess The 3rd Image Country",style = TextStyle(color =textColour3, background = backgroundColour3)
                )},

                )
        }
        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)) {
            Text(text = "Wrong answer count - $inCorrectCount/3")

        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {

            Log.e(userInputList.toString(),"userInputList")
            Log.e(userInputList[0],"userInputList0")
            Log.e(userInputList[1],"userInputList1")
            Button(onClick = {
//               User click Submit button  check usreinputs are correct or incorrect and change background colour add display "Correct" or "wrong"
                val allElements = allImageNameList.containsAll(userInputList)
                if (buttonText=="Submit"){
                    marks=3
                    if (allElements){
                        Correct="CORRECT!"
                        buttonText="Next"

                        isButtonEnabled1.value=false
                        backgroundColour1= Color.Green
                        textColour1= Color.White

                        isButtonEnabled2.value=false
                        backgroundColour2= Color.Green
                        textColour2= Color.White

                        isButtonEnabled3.value=false
                        backgroundColour3= Color.Green
                        textColour3= Color.White
                    }else{
                        ++inCorrectCount
                        for (element in userInputList) {
                            if (allImageNameList.contains(element)) {
                                if (element==imageName1.lowercase()){
                                    isButtonEnabled1.value=false
                                    backgroundColour1= Color.Green
                                    textColour1= Color.White

                                }else if(element==imageName2.lowercase()){
                                    isButtonEnabled2.value=false
                                    backgroundColour2= Color.Green
                                    textColour2= Color.White

                                }else{
                                    isButtonEnabled3.value=false
                                    backgroundColour3= Color.Green
                                    textColour3= Color.White

                                }

                            }else if(userInputList.all { it.isEmpty() }) {
                                backgroundColour1 = Color.Red
                                textColour1 = Color.White
                                backgroundColour2 = Color.Red
                                textColour2 = Color.White
                                backgroundColour3 = Color.Red
                                textColour3 = Color.White
                                marks=0
                            }else if(element==userInputLatter1 && backgroundColour1==Color.White){
                                backgroundColour1= Color.Red
                                textColour1= Color.White
                                --marks

                            }else if (element==userInputLatter2 && backgroundColour2==Color.White) {
                                backgroundColour2 = Color.Red
                                textColour2 = Color.White
                                --marks
                            }else  {
                                Log.e(element,"element")
                                Log.e(userInputLatter2,"userInputLatter2")
                                Log.e(userInputLatter2,"userInputLatter2")
                                backgroundColour3 = Color.Red
                                textColour3 = Color.White
                                --marks


                            }
                        }
                    }

                }else{
                    //User click next button generate all numbers and other variables set default value

                    userInputLatter1=""
                    userInputLatter2=""
                    userInputLatter3=""
                    Correct=""
                    buttonText="Submit"
                    isButtonEnabled1.value =true
                    isButtonEnabled2.value =true
                    isButtonEnabled3.value =true
                    backgroundColour1= Color.White
                    textColour1=Color.Black
                    backgroundColour2= Color.White
                    textColour2=Color.Black
                    backgroundColour3= Color.White
                    textColour3=Color.Black
                    userInputList.clear()
                    allImageNameList.clear()
                    inCorrectCount=0
                    marks=0


                    var newRandomIndex1 = Random.nextInt(0, imageList.size)
                    var newRandomIndex2 = Random.nextInt(0, imageList.size)
                    var newRandomIndex3 = Random.nextInt(0, imageList.size)
                    var randomIndexList= listOf(randomIndex1,randomIndex2,randomIndex3)
                    var newRandomList = listOf(newRandomIndex1,newRandomIndex2,newRandomIndex3)

                    for (element in newRandomList) {
                        if (randomIndexList.contains(element)) {
                            allElementsExist = true
                            break
                        }
                    }
                    if (allElementsExist) {
                        newRandomIndex1=Random.nextInt(0, imageList.size)
                        newRandomIndex2=Random.nextInt(0, imageList.size)
                        newRandomIndex3=Random.nextInt(0, imageList.size)
                        randomIndex1=newRandomIndex1
                        randomIndex2=newRandomIndex2
                        randomIndex3=newRandomIndex3
                    }
                    randomIndex1=newRandomIndex1
                    randomIndex2=newRandomIndex2
                    randomIndex3=newRandomIndex3

                }


            }) {
                Text(text = buttonText)
            }
        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            if (Correct=="CORRECT!"){
                Text(text = Correct,color = Color.Green, fontSize = 18.sp)
            }else{
                Column {
//                    Display  Correct image name for user incorrect user input
                    Row {
                        Text(text = Correct,color = Color.Red, fontSize = 18.sp)
                    }
                    if (inCorrectCount>=3){
                        Row {
                            if(backgroundColour1==Color.Red){
                                Text(text ="1st Image Name -$imageName1" ,color = Color.Blue, fontSize = 18.sp, textAlign = TextAlign.Center)
                            }

                        }
                        Row {
                            if (backgroundColour2==Color.Red){
                                Text(text = "2nd Image Name -$imageName2",color = Color.Blue, fontSize = 18.sp, textAlign = TextAlign.Center)

                            }
                        }
                        Row {
                            if (backgroundColour3==Color.Red){
                                Text(text = "3rd Image Name -$imageName3",color = Color.Blue, fontSize = 18.sp, textAlign = TextAlign.Center)
                            }
                        }
                    }

                }
            }

        }

    }

}

//val ColorSaver = run {
//    val redKey = "Red"
//    val greenKey = "Green"
//    val blueKey = "Blue"
//    mapSaver(
//        save = { mapOf(redKey to it.red, greenKey to it.green, blueKey to it.blue) },
//        restore = {
//            Color(
//                red = it[redKey] as Float,
//                green = it[greenKey] as Float,
//                blue = it[blueKey] as Float
//            )
//        }
//    )
//}





@Composable
fun PreviewRandomImage4(checked:Boolean) {
    var newCheck by remember { mutableStateOf(checked) }
    val imageList = listOf(
        R.drawable.ad,
        R.drawable.ae,
        R.drawable.af,
        R.drawable.ag,
        R.drawable.ai,
        R.drawable.al,
        R.drawable.am,
        R.drawable.ao,
        R.drawable.aq,
        R.drawable.ar,
        R.drawable.`as`,
        R.drawable.at,
        R.drawable.au,
        R.drawable.aw,
        R.drawable.ax,
        R.drawable.az,
        R.drawable.ba,
        R.drawable.bb,
        R.drawable.bd,
        R.drawable.be,
        R.drawable.bf,
        R.drawable.bg,
        R.drawable.bh,
        R.drawable.bi,
        R.drawable.bj,
        R.drawable.bl,
        R.drawable.bm,
        R.drawable.bn,
        R.drawable.bo,
        R.drawable.bq,
        R.drawable.br,
        R.drawable.bs,
        R.drawable.bt,
        R.drawable.bv,
        R.drawable.bw,
        R.drawable.by,
        R.drawable.bz,
        R.drawable.ca,
        R.drawable.cc,
        R.drawable.cd,
        R.drawable.cf,
        R.drawable.cg,
        R.drawable.ch,
        R.drawable.ci,
        R.drawable.ck,
        R.drawable.cl,
        R.drawable.cm,
        R.drawable.cn,
        R.drawable.co,
        R.drawable.cr,
        R.drawable.cu,
        R.drawable.cv,
        R.drawable.cw,
        R.drawable.cx,
        R.drawable.cy,
        R.drawable.cz,
        R.drawable.de,
        R.drawable.dj,
        R.drawable.dk,
        R.drawable.dm,
        R.drawable.resource_do,
        R.drawable.dz,
        R.drawable.ec,
        R.drawable.ee,
        R.drawable.eg,
        R.drawable.eh,
        R.drawable.er,
        R.drawable.es,
        R.drawable.et,
        R.drawable.eu,
        R.drawable.fi,
        R.drawable.fj,
        R.drawable.fk,
        R.drawable.fm,
        R.drawable.fo,
        R.drawable.fr,
        R.drawable.ga,
        R.drawable.gb,
        R.drawable.gbeng,
        R.drawable.gbnir,
        R.drawable.gbsct,
        R.drawable.gbwls,
        R.drawable.gd,
        R.drawable.ge,
        R.drawable.gf,
        R.drawable.gg,
        R.drawable.gh,
        R.drawable.gi,
        R.drawable.gl,
        R.drawable.gm,
        R.drawable.gn,
        R.drawable.gp,
        R.drawable.gq,
        R.drawable.gr,
        R.drawable.gs,
        R.drawable.gt,
        R.drawable.gu,
        R.drawable.gw,
        R.drawable.gy,
        R.drawable.hk,
        R.drawable.hm,
        R.drawable.hn,
        R.drawable.hr,
        R.drawable.ht,
        R.drawable.hu,
        R.drawable.id,
        R.drawable.ie,
        R.drawable.il,
        R.drawable.im,
        R.drawable.`in`,
        R.drawable.io,
        R.drawable.iq,
        R.drawable.ir,
        R.drawable.`is`,
        R.drawable.it,
        R.drawable.je,
        R.drawable.jm,
        R.drawable.jo,
        R.drawable.jp,
        R.drawable.ke,
        R.drawable.kg,
        R.drawable.kh,
        R.drawable.ki,
        R.drawable.km,
        R.drawable.kn,
        R.drawable.kp,
        R.drawable.kr,
        R.drawable.kw,
        R.drawable.ky,
        R.drawable.kz,
        R.drawable.la,
        R.drawable.lb,
        R.drawable.lc,
        R.drawable.li,
        R.drawable.lk,
        R.drawable.lr,
        R.drawable.ls,
        R.drawable.lt,
        R.drawable.lu,
        R.drawable.lv,
        R.drawable.ly,
        R.drawable.ma,
        R.drawable.mc,
        R.drawable.md,
        R.drawable.me,
        R.drawable.mf,
        R.drawable.mg,
        R.drawable.mh,
        R.drawable.mk,
        R.drawable.ml,
        R.drawable.mm,
        R.drawable.mn,
        R.drawable.mo,
        R.drawable.mp,
        R.drawable.mq,
        R.drawable.mr,
        R.drawable.ms,
        R.drawable.mt,
        R.drawable.mu,
        R.drawable.mv,
        R.drawable.mw,
        R.drawable.mx,
        R.drawable.my,
        R.drawable.mz,
        R.drawable.na,
        R.drawable.nc,
        R.drawable.ne,
        R.drawable.nf,
        R.drawable.ng,
        R.drawable.ni,
        R.drawable.nl,
        R.drawable.no,
        R.drawable.np,
        R.drawable.nr,
        R.drawable.nu,
        R.drawable.nz,
        R.drawable.om,
        R.drawable.pa,
        R.drawable.pe,
        R.drawable.pf,
        R.drawable.pg,
        R.drawable.ph,
        R.drawable.pk,
        R.drawable.pl,
        R.drawable.pm,
        R.drawable.pn,
        R.drawable.pr,
        R.drawable.ps,
        R.drawable.pt,
        R.drawable.pw,
        R.drawable.py,
        R.drawable.qa,
        R.drawable.re,
        R.drawable.ro,
        R.drawable.rs,
        R.drawable.ru,
        R.drawable.rw,
        R.drawable.sa,
        R.drawable.sb,
        R.drawable.sc,
        R.drawable.sd,
        R.drawable.se,
        R.drawable.sg,
        R.drawable.sh,
        R.drawable.si,
        R.drawable.sj,
        R.drawable.sk,
        R.drawable.sl,
        R.drawable.sm,
        R.drawable.sn,
        R.drawable.so,
        R.drawable.sr,
        R.drawable.ss,
        R.drawable.st,
        R.drawable.sv,
        R.drawable.sx,
        R.drawable.sy,
        R.drawable.sz,
        R.drawable.tc,
        R.drawable.td,
        R.drawable.tf,
        R.drawable.tg,
        R.drawable.th,
        R.drawable.tj,
        R.drawable.tk,
        R.drawable.tl,
        R.drawable.tm,
        R.drawable.tn,
        R.drawable.to,
        R.drawable.tr,
        R.drawable.tt,
        R.drawable.tv,
        R.drawable.tw,
        R.drawable.tz,
        R.drawable.ua,
        R.drawable.ug,
        R.drawable.um,
        R.drawable.us,
        R.drawable.uy,
        R.drawable.uz,
        R.drawable.va,
        R.drawable.vc,
        R.drawable.ve,
        R.drawable.vg,
        R.drawable.vi,
        R.drawable.vn,
        R.drawable.vu,
        R.drawable.wf,
        R.drawable.ws,
        R.drawable.xk,
        R.drawable.ye,
        R.drawable.yt,
        R.drawable.za,
        R.drawable.zm,
        R.drawable.zw,


        )
    val countryList = listOf(
        "Andorra",
        "United Arab Emirates",
        "Afghanistan",
        "Antigua and Barbuda",
        "Anguilla",
        "Albania",
        "Armenia",
        "Angola",
        "Antarctica",
        "Argentina",
        "American Samoa",
        "Austria",
        "Australia",
        "Aruba",
        "\u00c5land Islands",
        "Azerbaijan",
        "Bosnia and Herzegovina",
        "Barbados",
        "Bangladesh",
        "Belgium",
        "Burkina Faso",
        "Bulgaria",
        "Bahrain",
        "Burundi",
        "Benin",
        "Saint Barthélemy",
        "Bermuda",
        "Brunei Darussalam",
        "Bolivia, Plurinational State of",
        "Caribbean Netherlands",
        "Brazil",
        "Bahamas",
        "Bhutan",
        "Bouvet Island",
        "Botswana",
        "Belarus",
        "Belize",
        "Canada",
        "Cocos (Keeling) Islands",
        "Congo",
        "Central African Republic",
        "Republic of the Congo",
        "Switzerland",
        "Côte d'Ivoire",
        "Cook Islands",
        "Chile",
        "Cameroon",
        "China",
        "Colombia",
        "Costa Rica",
        "Cuba",
        "Cape Verde",
        "Cura\u00e7ao",
        "Christmas Island",
        "Cyprus",
        "Czech Republic",
        "Germany",
        "Djibouti",
        "Denmark",
        "Dominica",
        "Dominican Republic",
        "Algeria",
        "Ecuador",
        "Estonia",
        "Egypt",
        "Western Sahara",
        "Eritrea",
        "Spain",
        "Ethiopia",
        "Europe",
        "Finland",
        "Fiji",
        "Falkland Islands",
        "Micronesia",
        "Faroe Islands",
        "France",
        "Gabon",
        "United Kingdom",
        "England",
        "Northern Ireland",
        "Scotland",
        "Wales",
        "Grenada",
        "Georgia",
        "French Guiana",
        "Guernsey",
        "Ghana",
        "Gibraltar",
        "Greenland",
        "Gambia",
        "Guinea",
        "Guadeloupe",
        "Equatorial Guinea",
        "Greece",
        "South Georgia and the South Sandwich Islands",
        "Guatemala",
        "Guam",
        "Guinea-Bissau",
        "Guyana",
        "Hong Kong",
        "Heard Island and McDonald Islands",
        "Honduras",
        "Croatia",
        "Haiti",
        "Hungary",
        "Indonesia",
        "Ireland",
        "Israel",
        "Isle of Man",
        "India",
        "British Indian Ocean Territory",
        "Iraq",
        "Iran, Islamic Republic of",
        "Iceland",
        "Italy",
        "Jersey",
        "Jamaica",
        "Jordan",
        "Japan",
        "Kenya",
        "Kyrgyzstan",
        "Cambodia",
        "Kiribati",
        "Comoros",
        "Saint Kitts and Nevis",
        "Korea",
        "Korea, Republic of",
        "Kuwait",
        "Cayman Islands",
        "Kazakhstan",
        "Laos ",
        "Lebanon",
        "Saint Lucia",
        "Liechtenstein",
        "Sri Lanka",
        "Liberia",
        "Lesotho",
        "Lithuania",
        "Luxembourg",
        "Latvia",
        "Libya",
        "Morocco",
        "Monaco",
        "Moldova, Republic of",
        "Montenegro",
        "Saint Martin",
        "Madagascar",
        "Marshall Islands",
        "North Macedonia",
        "Mali",
        "Myanmar",
        "Mongolia",
        "Macao",
        "Northern Mariana Islands",
        "Martinique",
        "Mauritania",
        "Montserrat",
        "Malta",
        "Mauritius",
        "Maldives",
        "Malawi",
        "Mexico",
        "Malaysia",
        "Mozambique",
        "Namibia",
        "New Caledonia",
        "Niger",
        "Norfolk Island",
        "Nigeria",
        "Nicaragua",
        "Netherlands",
        "Norway",
        "Nepal",
        "Nauru",
        "Niue",
        "New Zealand",
        "Oman",
        "Panama",
        "Peru",
        "French Polynesia",
        "Papua New Guinea",
        "Philippines",
        "Pakistan",
        "Poland",
        "Saint Pierre and Miquelon",
        "Pitcairn",
        "Puerto Rico",
        "Palestine",
        "Portugal",
        "Palau",
        "Paraguay",
        "Qatar",
        "Réunion",
        "Romania",
        "Serbia",
        "Russian Federation",
        "Rwanda",
        "Saudi Arabia",
        "Solomon Islands",
        "Seychelles",
        "Sudan",
        "Sweden",
        "Singapore",
        "Saint Helena, Ascension and Tristan da Cunha",
        "Slovenia",
        "Svalbard and Jan Mayen Islands",
        "Slovakia",
        "Sierra Leone",
        "San Marino",
        "Senegal",
        "Somalia",
        "Suriname",
        "South Sudan",
        "Sao Tome and Principe",
        "El Salvador",
        "Sint Maarten (Dutch part)",
        "Syrian Arab Republic",
        "Swaziland",
        "Turks and Caicos Islands",
        "Chad",
        "French Southern Territories",
        "Togo",
        "Thailand",
        "Tajikistan",
        "Tokelau",
        "Timor-Leste",
        "Turkmenistan",
        "Tunisia",
        "Tonga",
        "Turkey",
        "Trinidad and Tobago",
        "Tuvalu",
        "Taiwan (Republic of China)",
        "Tanzania, United Republic of",
        "Ukraine",
        "Uganda",
        "US Minor Outlying Islands",
        "United States",
        "Uruguay",
        "Uzbekistan",
        "Holy See (Vatican City State)",
        "Saint Vincent and the Grenadines",
        "Venezuela, Bolivarian Republic of",
        "Virgin Islands, British",
        "Virgin Islands, U.S.",
        "Vietnam",
        "Vanuatu",
        "Wallis and Futuna Islands",
        "Samoa",
        "Kosovo",
        "Yemen",
        "Mayotte",
        "South Africa",
        "Zambia",
        "Zimbabwe"
    )
    AdvancedLevel(imageList, countryList,checked)
//    Log.d(imageList.size.toString(), "PreviewRandomImage: ")

}
