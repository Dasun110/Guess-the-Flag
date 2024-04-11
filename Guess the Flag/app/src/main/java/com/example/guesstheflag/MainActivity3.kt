package com.example.guesstheflag

//https://medium.com/@android-world/jetpack-compose-countdown-timer-9531dd3119a6
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val checked = intent.getBooleanExtra("checked", true)
            PreviewRandomImage2(checked)

        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GuessHintRandomImage(imageList: List<Int>,countryList: List<String>,checked: Boolean){
    var newChecked by rememberSaveable { mutableStateOf(checked) }
    // Generate a random index
    var randomIndex by rememberSaveable { mutableStateOf(Random.nextInt(0, imageList.size)) }
    // Get random image
    var imageResource = imageList[randomIndex]
    // Get image name
    var index = imageList.indexOf(imageResource)
    var imageName = countryList[index]
    var imageNameList =imageName.map { it.toString() }
//  Crate userInputLatter variable for get user input data
    var userInputLatter by rememberSaveable { mutableStateOf("") }
    var displayList:MutableList<String> = mutableListOf()
//  Add the display list "_"
    Log.e(imageName, "RandomImage: ", )

    var imageNameListSize=imageNameList.size
    for (i in 0..<imageNameListSize){
        if(imageNameList[i]==" "){
            displayList.add(" ")
        }else{
            displayList.add("_")
        }

    }

//  Displaylist all element convert to  string
    val displayListItem = displayList.joinToString(" ")
    var list by rememberSaveable { mutableStateOf(displayList) }
//    Log.e(list.toString(),"up")


    var correctLatter by rememberSaveable { mutableStateOf(displayListItem) }
    var incorrectCount by rememberSaveable { mutableStateOf(0) }
    var isCorrect by rememberSaveable { mutableStateOf("") }
    var timeLeft by rememberSaveable { mutableStateOf(10) }
    var buttonText by rememberSaveable { mutableStateOf("Submit") }


    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()){

            if (newChecked==true && buttonText=="Submit"){
//                Add the timer
                LaunchedEffect(key1 = timeLeft) {
                    while (timeLeft > 0) {
                        delay(1000L)
                        timeLeft--
                    }
                }
//              TImer is end auto submit and display user is correct or wrong
                if (timeLeft==0){
                    timeLeft=10
                    if (buttonText=="Submit"  && buttonText=="Submit"){
                        Log.e(imageNameListSize.toString(), "len1")
                        Log.e(displayList.size.toString(), "len2")
                        for (i in 0..<displayList.size) {
                            Log.e(i.toString(), "i")
                            Log.e(imageNameList[i].toString(), "i2")
                            if (imageNameList[i].lowercase() == userInputLatter) {
                                Log.e(imageNameList[i].toString(), "i5")
                                if (displayList[i] == "_")
                                    list[i] = userInputLatter
                                Log.e(displayList[i].toString(), "i0000")
                            }

                        }
                        val isInTheList = list.any { it.contains(userInputLatter, ignoreCase = true) }

                        if (!isInTheList){
                            ++incorrectCount
                        }
                        if (incorrectCount>=3){
                            isCorrect="WRONG!"
                            buttonText="Next"

                        }else if (!list.any { it.contains("_", ignoreCase = true) }){
                            isCorrect="CORRECT!"
                            buttonText="Next"
                        }

                        Log.e(displayList.toString(), "list")

                        Log.e(list.toString(), "list2")
                        correctLatter=list.joinToString(" ")
                    }else{
                        timeLeft=10
                        userInputLatter=""
                        correctLatter=""
                        incorrectCount=0
                        isCorrect=""
                        buttonText = "Submit"

                        var newRandomNum=Random.nextInt(0, imageList.size)
                        while (newRandomNum==randomIndex){
                            newRandomNum=Random.nextInt(0, imageList.size)
                            randomIndex=newRandomNum
                        }
//                    displayList= emptyList<String>().toMutableList()
                        randomIndex=newRandomNum
                        imageResource = imageList[newRandomNum]
                        index = imageList.indexOf(imageResource)
                        imageName = countryList[index]
                        imageNameList =imageName.map { it.toString() }
                        for (i in 0..<imageNameListSize){
                            if(imageNameList[i]==" "){
                                displayList.add(" ")
                            }else{
                                displayList.add("_")
                            }

                        }

                        Log.e(displayList.toString(),"disply")

                        list=displayList
                    }

                }
                Text(text = "Time left: $timeLeft", fontSize = 24.sp, modifier = Modifier.background(Color.Gray).padding(top=10.dp))
            }
        }

        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = " Guess-Hints",
//                modifier = Modifier.padding(start = 100.dp),
                fontSize = 32.sp,
            )
        }
        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
//            Display image
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp, top = 20.dp, bottom = 20.dp)
                    .size(200.dp)
                    .border(BorderStroke(5.dp, Color.Black))
            )
        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {

            Text(text = correctLatter,fontSize = 24.sp,)

        }
        Row(modifier = Modifier.padding(start = 50.dp, top = 20.dp , bottom = 20.dp)) {
//            Crate outline text box for get user input
            OutlinedTextField(
                value = userInputLatter,
                onValueChange = {userInput->
                    if (userInput.length <= 1) {
                        userInputLatter = userInput.lowercase()
                    }
                },
                label = { Text("Guess The Country") }
            )
        }
        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
//            Display user incorrect  attempt count
            Text(text = "Wrong answer count - $incorrectCount/3")

        }
        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
//              if user click submit button check use input is correct or wrong
                if (buttonText=="Submit"){
                    Log.e(imageNameListSize.toString(), "len1")
                    Log.e(displayList.size.toString(), "len2")
                    for (i in 0..<displayList.size) {
                        Log.e(i.toString(), "i")
                        Log.e(imageNameList[i].toString(), "i2")
                        if (imageNameList[i].lowercase() == userInputLatter) {
                            Log.e(imageNameList[i].toString(), "i5")
                            if (displayList[i] == "_")
                                list[i] = userInputLatter
                            Log.e(displayList[i].toString(), "i0000")
                        }

                    }
                    val isInTheList = list.any { it.contains(userInputLatter, ignoreCase = true) }

                    if (!isInTheList){
                        ++incorrectCount
                    }
                    if (incorrectCount>=3){
                        isCorrect="WRONG!"
                        buttonText="Next"

                    }else if (!list.any { it.contains("_", ignoreCase = true) }){
                        isCorrect="CORRECT!"
                        buttonText="Next"
                    }

                    Log.e(displayList.toString(), "list")

                    Log.e(list.toString(), "list2")
                    correctLatter=list.joinToString(" ")
                }else{
                    //User click next button generate all numbers and other variables set default value

                    userInputLatter=""
                    correctLatter=""
                    incorrectCount=0
                    isCorrect=""
                    buttonText = "Submit"

                    var newRandomNum=Random.nextInt(0, imageList.size-1)
                    while (newRandomNum==randomIndex){
                        newRandomNum=Random.nextInt(0, imageList.size-1)
                        randomIndex=newRandomNum
                    }
//                    displayList= emptyList<String>().toMutableList()
                    randomIndex=newRandomNum
                    imageResource = imageList[newRandomNum]
                    index = imageList.indexOf(imageResource)
                    imageName = countryList[index]
                    imageNameList =imageName.map { it.toString() }
                    for (i in 0..<imageNameListSize-1){
                        if(imageNameList[i]==" "){
                            displayList.add(" ")
                        }else{
                            displayList.add("_")
                        }

                    }

                    Log.e(displayList.toString(),"disply")

                    list=displayList
                }


                Log.e(correctLatter.toString(), "latter")
            }) {
                Text(text = buttonText)
                Log.e(correctLatter.toString(), "latter")
            }

        }
        Row {
//            Display user is "Correct" or "wrong
            if (isCorrect=="WRONG!"){
                Column {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()) {
                        Text(text = isCorrect, color = Color.Red, fontSize = 18.sp,
                            textAlign = TextAlign.Center)
                    }
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()) {


                        Text(text = "Correct answer is $imageName", color = Color.Blue, fontSize = 18.sp,
                            textAlign = TextAlign.Center)
                    }

                }

            }else{
                Text(text = isCorrect, color = Color.Green, fontSize = 18.sp,
                    textAlign = TextAlign.Center)
            }


        }



    }

}




// Add all image in imageList and all country name add countryList
@Composable
fun PreviewRandomImage2(checked:Boolean) {
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
    GuessHintRandomImage(imageList, countryList,newCheck)
//    Log.d(imageList.size.toString(), "PreviewRandomImage: ")

}

