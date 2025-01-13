package com.example.constellationapp

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager

@Composable
fun SunScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    // 从 SharedPreferences 加载数据
    var nickname by remember { mutableStateOf(loadData(context, "nickname", "")) }
    var birthday by remember { mutableStateOf(LocalDate.parse(loadData(context, "birthday", LocalDate.now().toString()))) }
    var gender by remember { mutableStateOf(loadData(context, "gender", "男")) }
    var mbti by remember { mutableStateOf(loadData(context, "mbti", "")) }
    var occupation by remember { mutableStateOf(loadData(context, "occupation", "")) }
    var wechatId by remember { mutableStateOf(loadData(context, "wechatId", "")) }
    var qqNumber by remember { mutableStateOf(loadData(context, "qqNumber", "")) }

    val age = calculateAge(birthday)
    val constellation = getConstellation(birthday)

    // 保存数据到 SharedPreferences 的函数
    fun saveData(key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // 浅灰色背景
            .verticalScroll(scrollState)
            .padding(top = 64.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_sun), // 假设有一个名为 ic_sun 的 drawable 图片资源
            contentDescription = "Sun Icon",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "个人信息",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A90E2), // 深蓝色
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nickname,
            onValueChange = {
                nickname = it
                saveData("nickname", it)
            },
            label = { Text("昵称") },
            placeholder = { Text("请输入昵称") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        DatePickerField(label = "生日", date = birthday, onDateSelected = {
            birthday = it
            saveData("birthday", it.toString())
        }, focusManager = focusManager)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "性别")
            RadioGroup(genderOptions = listOf("男", "女"), selectedOption = gender, onSelect = {
                gender = it
                saveData("gender", it)
            })
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = mbti,
            onValueChange = {
                mbti = it
                saveData("mbti", it)
            },
            label = { Text("MBTI") },
            placeholder = { Text("请输入MBTI类型") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        AgeAndConstellationField(
            label = "年龄",
            value = age.toString(),
            tooltipText = "填写生日后自动获取"
        )

        Spacer(modifier = Modifier.height(8.dp))

        AgeAndConstellationField(
            label = "星座",
            value = constellation,
            tooltipText = "填写生日后自动获取"
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = occupation,
            onValueChange = {
                occupation = it
                saveData("occupation", it)
            },
            label = { Text("职业") },
            placeholder = { Text("请输入职业") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = wechatId,
            onValueChange = {
                wechatId = it
                saveData("wechatId", it)
            },
            label = { Text("微信号") },
            placeholder = { Text("请输入微信号") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = qqNumber,
            onValueChange = {
                qqNumber = it
                saveData("qqNumber", it)
            },
            label = { Text("QQ号") },
            placeholder = { Text("请输入QQ号") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DatePickerField(label: String, date: LocalDate, onDateSelected: (LocalDate) -> Unit, focusManager: FocusManager) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var expanded by remember { mutableStateOf(false) }
    var displayText by remember { mutableStateOf(date.format(formatter)) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = displayText,
            onValueChange = { /* Do nothing here */ },
            label = { Text(label) },
            placeholder = { Text("请选择日期") },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.CalendarToday, contentDescription = "Calendar")
                }
            }
        )

        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                DatePicker(selectedDate = date, onDateSelected = { newDate ->
                    displayText = newDate.format(formatter)
                    onDateSelected(newDate)
                    expanded = false
                }, focusManager = focusManager)
            }
        }
    }
}

@Composable
fun DatePicker(selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit, focusManager: FocusManager) {
    val yearRange = 1900..LocalDate.now().year
    val years = (yearRange.first..yearRange.last).toList()
    val months = (1..12).map { "%02d".format(it) }
    val daysInMonth = when (selectedDate.monthValue) {
        2 -> if (isLeapYear(selectedDate.year)) 29 else 28
        4, 6, 9, 11 -> 30
        else -> 31
    }
    val days = (1..daysInMonth).map { "%02d".format(it) }

    var year by remember { mutableStateOf(selectedDate.year.toString()) }
    var month by remember { mutableStateOf("%02d".format(selectedDate.monthValue)) }
    var day by remember { mutableStateOf("%02d".format(selectedDate.dayOfMonth)) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                label = { Text("年份") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                modifier = Modifier.width(100.dp)
            )

            OutlinedTextField(
                value = month,
                onValueChange = { month = it.trimStart('0') },
                label = { Text("月份") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                modifier = Modifier.width(100.dp)
            )

            OutlinedTextField(
                value = day,
                onValueChange = { day = it.trimStart('0') },
                label = { Text("日期") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                modifier = Modifier.width(100.dp)
            )
        }

        Button(
            onClick = {
                try {
                    val parsedYear = year.toIntOrNull() ?: throw NumberFormatException()
                    val parsedMonth = month.toIntOrNull() ?: throw NumberFormatException()
                    val parsedDay = day.toIntOrNull() ?: throw NumberFormatException()

                    if (parsedMonth < 1 || parsedMonth > 12 || parsedDay < 1 || parsedDay > daysInMonth(parsedYear, parsedMonth)) {
                        showError = true
                        return@Button
                    }

                    val newDate = LocalDate.of(parsedYear, parsedMonth, parsedDay)
                    onDateSelected(newDate)
                    showError = false
                } catch (e: Exception) {
                    showError = true
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("确认")
        }

        if (showError) {
            Text(
                text = "填写错了吗？再检查一下吧",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun daysInMonth(year: Int, month: Int): Int {
    return when (month) {
        2 -> if (isLeapYear(year)) 29 else 28
        4, 6, 9, 11 -> 30
        else -> 31
    }
}

@Composable
fun RadioGroup(genderOptions: List<String>, selectedOption: String, onSelect: (String) -> Unit) {
    genderOptions.forEach { option ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            RadioButton(
                selected = (option == selectedOption),
                onClick = { onSelect(option) }
            )
            Text(option, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun AgeAndConstellationField(label: String, value: String, tooltipText: String) {
    var showTooltip by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            label = { Text(label) },
            placeholder = { Text("自动计算") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { showTooltip = !showTooltip }) {
                    Icon(Icons.Default.Info, contentDescription = "Info")
                }
            }
        )

        if (showTooltip) {
            Popup(
                alignment = Alignment.BottomEnd,
                properties = PopupProperties(focusable = true, dismissOnBackPress = true, dismissOnClickOutside = true),
                onDismissRequest = { showTooltip = false }
            ) {
                Card(
                    backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                    elevation = 4.dp,
                    modifier = Modifier.padding(end = 4.dp, top = 4.dp)
                ) {
                    Text(
                        text = tooltipText,
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun calculateAge(birthDate: LocalDate): Int {
    val today = LocalDate.now()
    return Period.between(birthDate, today).years
}

@Composable
fun getConstellation(birthDate: LocalDate): String {
    val month = birthDate.monthValue
    val day = birthDate.dayOfMonth

    return when {
        (month == 1 && day >= 20) || (month == 2 && day <= 18) -> "水瓶座"
        (month == 2 && day >= 19) || (month == 3 && day <= 20) -> "双鱼座"
        (month == 3 && day >= 21) || (month == 4 && day <= 19) -> "白羊座"
        (month == 4 && day >= 20) || (month == 5 && day <= 20) -> "金牛座"
        (month == 5 && day >= 21) || (month == 6 && day <= 21) -> "双子座"
        (month == 6 && day >= 22) || (month == 7 && day <= 22) -> "巨蟹座"
        (month == 7 && day >= 23) || (month == 8 && day <= 22) -> "狮子座"
        (month == 8 && day >= 23) || (month == 9 && day <= 22) -> "处女座"
        (month == 9 && day >= 23) || (month == 10 && day <= 23) -> "天秤座"
        (month == 10 && day >= 24) || (month == 11 && day <= 22) -> "天蝎座"
        (month == 11 && day >= 23) || (month == 12 && day <= 21) -> "射手座"
        (month == 12 && day >= 22) || (month == 1 && day <= 19) -> "摩羯座"
        else -> "未知"
    }
}

fun isLeapYear(year: Int): Boolean {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}

// 加载数据
fun loadData(context: Context, key: String, defaultValue: String): String {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, defaultValue) ?: defaultValue
}



