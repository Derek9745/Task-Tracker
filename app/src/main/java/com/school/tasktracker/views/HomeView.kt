package com.school.tasktracker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.school.tasktracker.components.TextRow
import com.school.tasktracker.data.MainViewModel
import com.school.tasktracker.data.Task
import com.school.tasktracker.ui.theme.TaskTrackerTheme
import com.school.tasktracker.components.HalfStarIcon

@Composable
fun HomeView(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    // Separate between priority lists
    val tasks = viewModel.tasks
    viewModel.addTask(Task(title = "Example", description = "Due soon", date = "", time = "", isPriority = false))
    viewModel.addTask(Task(title = "Example", description = "Due soon", date = "", time = "", isPriority = false))
    viewModel.addTask(Task(title = "Example", description = "Due soon", date = "", time = "", isPriority = false))
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        // Have to wrap priorites text in a box
        // So I can easily dyanmically center it
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (tasks.value.isNullOrEmpty()) "There are currently no tasks" else "Tasks",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }
        PriorityComposable(isPriority = true, viewModel = viewModel)
        PriorityComposable(isPriority = false, viewModel = viewModel)
    }
}

@Composable
fun ColoredLine(modifier: Modifier = Modifier, color: Color) {
    HorizontalDivider(
        modifier = modifier
            .width(30.dp)
            .offset(x = (-5).dp),
        color = color,
        thickness = 2.dp
    )
}

@Composable
fun PriorityComposable(
    modifier: Modifier = Modifier,
    isPriority: Boolean,
    viewModel: MainViewModel
) {
    val tasks = viewModel.tasks
    Column {
        Row {
            TextRow(
                color = Color.Gray,
                weight = FontWeight.ExtraBold,
                title = if (isPriority) "High Priority" else "Low Priority"
            )
            Spacer(modifier = modifier.width(4.dp))
            if (isPriority) {
                HalfStarIcon(filled = true)
            }
        }
        Spacer(modifier = modifier.height(5.dp))
        ColoredLine(color = if (isPriority) Color.Red else Color.Blue)
    }
    LazyRow {
        items(tasks.value!!) { item ->
            TaskDesign(
                title = item.title,
                days = 0,
                color = if (isPriority) Color(red = 245, green = 104, blue = 115) else Color(red = 115, green = 152, blue = 245)
            )
            Spacer(
                modifier = modifier.width(10.dp)
            )
        }
    }
}

@Composable
fun TaskDesign(modifier: Modifier = Modifier, title: String, days: Int, color: Color) {
    Box(
        modifier = modifier
            .background(color = color)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = modifier
                .padding(15.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(
                modifier = modifier
                    .height(25.dp)
            )
            Text(
                text = "Time Left: $days Days",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    TaskTrackerTheme {
        Surface {
            HomeView(viewModel = viewModel())
//            PriorityComposable(viewModel = viewModel(), isPriority = false)
//            ColoredLine(color = Color.Blue)
        }
    }
}