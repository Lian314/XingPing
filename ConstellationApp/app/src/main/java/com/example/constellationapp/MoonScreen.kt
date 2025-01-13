package com.example.constellationapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape // 导入 RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun MoonScreen() {
    val randomMessages = listOf(
        "愿你每天都有好心情！",
        "幸福安康，事事顺心。",
        "新的一天，新的开始。",
        "心想事成，万事如意。",
        "保持微笑，每一天都会更好。",
        "前程似锦，未来可期。",
        "一切顺利，梦想成真。",
        "快乐无限，幸福常伴。",
        "勇往直前，无所畏惧。",
        "心想事成，万事如意。",
        "阳光明媚，心情愉快。",
        "岁月静好，现世安稳。",
        "笑口常开，好运连连。",
        "前路光明，梦想成真。",
        "平安喜乐，事事顺遂。",
        "心想事成，万事如意。"
    )

    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // 浅灰色背景
            .padding(top = 64.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_moon), // 假设有一个名为 ic_moon 的 drawable 图片资源
            contentDescription = "Moon Icon",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "人生几何",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A90E2), // 深蓝色
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Clock()

        Spacer(modifier = Modifier.height(16.dp))

        Calendar()

        Spacer(modifier = Modifier.height(16.dp))

        if (message.isNotEmpty()) {
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
                        text = message,
                        fontSize = 18.sp,
                        color = getRandomColor(), // 使用随机颜色
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                // 处理按钮点击事件
                message = randomMessages.random()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4A90E2)), // 深蓝色背景
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(200.dp)
        ) {
            Text(
                text = "点击我",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Clock() {
    val currentTime = remember { mutableStateOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))) }
    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            delay(1000L)
        }
    }

    Card(
        backgroundColor = Color.White, // 白色卡片背景
        shape = CircleShape,
        elevation = 8.dp,
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = currentTime.value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A90E2), // 深蓝色
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Calendar() {
    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

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
                text = "当前日期",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A90E2), // 深蓝色
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currentDate,
                fontSize = 18.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun getRandomColor(): Color {
    val colors = listOf(
        Color(0xFFFFC107), // 黄色
        Color(0xFF4CAF50), // 绿色
        Color(0xFFF44336), // 红色
        Color(0xFF9C27B0), // 紫罗兰
        Color(0xFF2196F3), // 蓝色
        Color(0xFFFF9800), // 橙色
        Color(0xFF00BCD4), // 青色
        Color(0xFFE91E63), // 粉红色
        Color(0xFF673AB7), // 深紫色
        Color(0xFF009688)  // 青绿色
    )
    return colors[Random.nextInt(colors.size)]
}



