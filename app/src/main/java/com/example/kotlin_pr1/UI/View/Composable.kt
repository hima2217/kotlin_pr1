package com.example.kotlin_pr1.UI.View

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.*
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
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.w3c.dom.Text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import kotlinx.coroutines.launch

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
fun StudentListScreen(students: List<Student>, navigateToDetail: (Student) -> Unit) {
    // Экран со списком студентов
    // Можно использовать LazyColumn для отображения списка студентов
    LazyColumn {
        items(students) { student ->
            // Нажатие на студента должно вызвать переход на экран деталей
            Text(
                text = "ФИО: ${student.name}\nГруппа: ${student.group}",
                style = textStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateToDetail(student) }
            )
        }
    }
}

@Composable
fun StudentDetailScreen(student: Student) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ФИО: ${student.name}\nГруппа: ${student.group}",
            style = textStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(students: List<Student>) {
    val navController = rememberNavController()

    //val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        //scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Список студентов")
                }
            )
        },

        bottomBar = {
            // BottomAppBar - добавьте здесь элементы нижней панели
            BottomAppBar(
                containerColor = Color.Blue
                //Shape = CircleShape
            ) {
                // Добавьте элементы нижней панели
                IconButton(
                    onClick = {
                        // Обработка нажатия на элемент нижней панели
                        scope.launch {
                            //scaffoldState.drawerState.open()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Меню")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "studentList"
        ) {
            composable("studentList") {
                // Экран со списком студентов
                StudentListScreen(students) { student ->
                    navController.navigate("studentDetail/${student.name}/${student.group}")
                }
            }
            composable(
                "studentDetail/{studentName}/{group}",
                arguments = listOf(
                    navArgument("studentName") { type = NavType.StringType },
                    navArgument("group") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                // Экран с подробной информацией о студенте
                val studentName = backStackEntry.arguments?.getString("studentName")
                val group = backStackEntry.arguments?.getString("group")
                if (studentName != null && group != null) {
                    val student = students.find { it.name == studentName && it.group == group }
                    if (student != null) {
                        StudentDetailScreen(student)
                    }
                }
            }
        }
    }
}