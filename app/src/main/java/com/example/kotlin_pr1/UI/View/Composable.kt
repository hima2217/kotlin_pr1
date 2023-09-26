package com.example.kotlin_pr1.UI.View

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import org.w3c.dom.Text

data class Student(val name: String, val group: String)

private val textStyle =

    TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
// Другие стили
/*
   TextStyle(
       fontFamily = FontFamily.Serif,
       fontSize = 21.sp,
       fontWeight = FontWeight.Light,
       color = Color.Magenta
   )
   */


@Composable
fun StudentInfo(studentName: String, group: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ФИО: $studentName\nГруппа: $group",
            style = textStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun MyApp(students: List<Student>) {
    MaterialTheme(
        typography = Typography(
            TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        ),
        content = {
            Surface(
                color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    items(students) { student ->
                        StudentInfo(student.name, student.group)
                    }
                }
            }
        }
    )
}