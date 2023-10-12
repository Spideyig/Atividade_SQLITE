package com.example.atvdsqlite

import com.example.atvdsqlite.db.DBHandler
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.atvdsqlite.model.CourseModel
import com.example.atvdsqlite.ui.theme.AtvdSQLITETheme


class ViewCourses : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtvdSQLITETheme {

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {

                            TopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                                title = {
                                    Text(
                                        text = "GFG",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        color = Color.White
                                    )
                                })
                        }
                    ) {
                        readDataFromDatabase(LocalContext.current)
                    }
                }
            }
        }
    }
}

@Composable
fun readDataFromDatabase(context: Context) {
    lateinit var courseList: List<CourseModel>
    courseList = ArrayList<CourseModel>()

    val dbHandler: DBHandler = DBHandler(context);
    courseList = dbHandler.readCourses()!!

    LazyColumn {
        itemsIndexed(courseList) { index, item ->
            Card(
                modifier = Modifier.padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = courseList[index].courseName,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Course Tracks : " + courseList[index].courseTracks,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Course Duration : " + courseList[index].courseDuration,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Description : " + courseList[index].courseDescription,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}