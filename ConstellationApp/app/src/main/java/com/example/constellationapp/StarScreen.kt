package com.example.constellationapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.WbTwilight
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip // 导入 clip

private val constellationInfo = mapOf(
    "白羊座" to Triple(
        "基本信息：3月21日－4月19日",
        "火象星座",
        "人格特征：热情勇敢，行动力强，但有时冲动缺乏耐心。他们喜欢冒险和挑战自我，对待生活充满激情。"
    ),
    "金牛座" to Triple(
        "基本信息：4月20日－5月20日",
        "土象星座",
        "人格特征：稳重踏实，物质追求高，但固执且慢热。他们重视稳定性和安全感，善于积累财富和资源。"
    ),
    "双子座" to Triple(
        "基本信息：5月21日－6月21日",
        "风象星座",
        "人格特征：机智灵活，社交能力强，但善变且缺乏持久力。他们聪明好动，善于沟通交流，好奇心旺盛。"
    ),
    "巨蟹座" to Triple(
        "基本信息：6月22日－7月22日",
        "水象星座",
        "人格特征：情感丰富，家庭观念重，但敏感多疑易情绪化。他们非常重视家人和朋友，内心世界复杂而细腻。"
    ),
    "狮子座" to Triple(
        "基本信息：7月23日－8月22日",
        "火象星座",
        "人格特征：自信热情，领导力强，但骄傲自大喜欢掌控。他们天生领导者，有强烈的自我表达欲望。"
    ),
    "处女座" to Triple(
        "基本信息：8月23日－9月22日",
        "土象星座",
        "人格特征：细心谨慎，追求完美，但挑剔苛刻爱批判。他们注重细节，做事一丝不苟，对事物要求极高。"
    ),
    "天秤座" to Triple(
        "基本信息：9月23日－10月23日",
        "风象星座",
        "人格特征：追求和谐，优雅公正，但犹豫不决依赖他人。他们渴望平衡和美感，擅长调解冲突。"
    ),
    "天蝎座" to Triple(
        "基本信息：10月24日－11月21日",
        "水象星座",
        "人格特征：神秘敏锐，意志坚定，但占有欲强易记仇。他们洞察力强，忠诚度高，但也可能过于敏感。"
    ),
    "射手座" to Triple(
        "基本信息：11月22日－12月21日",
        "火象星座",
        "人格特征：乐观自由，爱冒险探索，但粗心大意不负责任。他们热爱旅行，向往自由，但有时会忽略细节。"
    ),
    "摩羯座" to Triple(
        "基本信息：12月22日－1月19日",
        "土象星座",
        "人格特征：务实勤奋，有责任感，但悲观压抑缺乏情趣。他们脚踏实地，目标明确，但有时显得严肃。"
    ),
    "水瓶座" to Triple(
        "基本信息：1月20日－2月18日",
        "风象星座",
        "人格特征：独立创新，思维独特，但冷漠固执不合群。他们思想开放，勇于尝试新事物，但也可能过于叛逆。"
    ),
    "双鱼座" to Triple(
        "基本信息：2月19日－3月20日",
        "水象星座",
        "人格特征：敏感温柔，富有同情心，但优柔寡断爱幻想。他们善良体贴，想象力丰富，但也容易迷茫。"
    )
)

@Composable
fun StarScreen(navController: NavHostController) {
    var selectedConstellation by remember { mutableStateOf("") }
    var basicInfo by remember { mutableStateOf("暂无") }
    var constellationType by remember { mutableStateOf("暂无") }
    var personality by remember { mutableStateOf("暂无") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "星⭐萍",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A90E2), // 深蓝色
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.Gray.copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenuExample(
            items = constellationInfo.keys.toList(),
            onItemSelected = { selected ->
                selectedConstellation = selected
                val (info, type, trait) = constellationInfo[selected]!!
                basicInfo = info
                constellationType = type
                personality = trait
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedConstellation.isNotEmpty()) {
            Card(
                backgroundColor = Color.White, // 白色卡片背景
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = constellationType,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (constellationType) {
                            "火象星座" -> Color(0xFFFFB3BA) // 淡红
                            "土象星座" -> Color(0xFFDDBEA9) // 淡棕
                            "风象星座" -> Color(0xFFC8E6C9) // 淡绿
                            "水象星座" -> Color(0xFFBBDEFB) // 淡蓝
                            else -> Color.Gray
                        },
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = basicInfo.replace("基本信息：", ""),
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = personality,
                        fontSize = 18.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { openWebPage(selectedConstellation, context) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4A90E2)), // 深蓝色
                modifier = Modifier.width(200.dp) // 缩小按钮宽度
            ) {
                Text(
                    text = "查看今日运势",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun DropdownMenuExample(items: List<String>, onItemSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }

    OutlinedTextField(
        value = if (selectedIndex == -1) "选择星座" else items[selectedIndex],
        onValueChange = {},
        readOnly = true,
        label = null,
        trailingIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Menu", tint = Color.Black)
            }
        },
        modifier = Modifier
            .width(200.dp) // 设置下拉菜单宽度
            .clickable { expanded = !expanded }
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF4A90E2), // 深蓝色
            unfocusedBorderColor = Color.Gray
        )
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.widthIn(min = 200.dp) // 设置下拉菜单最小宽度
    ) {
        items.forEachIndexed { index, item ->
            DropdownMenuItem(onClick = {
                selectedIndex = index
                expanded = false
                onItemSelected(item)
            }) {
                Text(text = item, color = Color.Black)
            }
        }
    }
}

private fun openWebPage(sign: String, context: Context) {
    val baseUrl = "https://www.xzw.com/fortune/"
    val signUrlPart = when (sign) {
        "白羊座" -> "aries"
        "金牛座" -> "taurus"
        "双子座" -> "gemini"
        "巨蟹座" -> "cancer"
        "狮子座" -> "leo"
        "处女座" -> "virgo"
        "天秤座" -> "libra"
        "天蝎座" -> "scorpio"
        "射手座" -> "sagittarius"
        "摩羯座" -> "capricorn"
        "水瓶座" -> "aquarius"
        "双鱼座" -> "pisces"
        else -> ""
    }

    if (signUrlPart.isNotEmpty()) {
        val url = "$baseUrl$signUrlPart/"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        context.startActivity(intent)
    } else {
        // Handle the case where the sign is not recognized
    }
}



