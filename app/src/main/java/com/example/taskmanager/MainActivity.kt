package com.example.taskcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskCounterApp()
        }
    }
}

@Composable
fun TaskCounterApp() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF287BFF),
            background = Color(0xFFF4F7FC),
            surface = Color.White
        )
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            TaskCounterScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun TaskCounterScreen(
    modifier: Modifier = Modifier
) {
    // Единственное изменяемое состояние экрана.
    var completedTasks by remember {
        mutableIntStateOf(0)
    }

    val totalTasks = 5

    // Эти значения вычисляются из состояния.
    val progress = completedTasks.toFloat() / totalTasks
    val allTasksCompleted = completedTasks == totalTasks

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Мои задачи",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$completedTasks из $totalTasks",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )

                // Фон полосы прогресса.
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .background(Color(0xFFE8EFFB))
                ) {
                    // Заполненная часть полосы.
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .height(14.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(
                                MaterialTheme.colorScheme.primary
                            )
                    )
                }

                Button(
                    onClick = {
                        if (completedTasks < totalTasks) {
                            completedTasks++
                        }
                    },
                    enabled = completedTasks < totalTasks,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Выполнить задачу")
                }

                Button(
                    onClick = {
                        if (completedTasks > 0) {
                            completedTasks--
                        }
                    },
                    enabled = completedTasks > 0,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEAF0FF),
                        contentColor = Color(0xFF183153)
                    )
                ) {
                    Text("Отменить")
                }

                if (allTasksCompleted) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFDDF5E5)
                        )
                    ) {
                        Text(
                            text = "Все задачи выполнены!",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            color = Color(0xFF176B3A),
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Button(
                    onClick = {
                        completedTasks = 0
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF0F1F5),
                        contentColor = Color(0xFF263238)
                    )
                ) {
                    Text("Сбросить")
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TaskCounterPreview() {
    TaskCounterApp()
}