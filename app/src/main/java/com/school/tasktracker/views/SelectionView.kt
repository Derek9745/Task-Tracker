package com.school.tasktracker.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.school.tasktracker.components.PriorityComposable
import com.school.tasktracker.components.TaskContent
import com.school.tasktracker.data.MainViewModel
import com.school.tasktracker.data.Task
import com.school.tasktracker.ui.theme.TaskTrackerTheme

@Composable
fun SelectionView(modifier: Modifier = Modifier, viewModel: MainViewModel, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        TaskContent(viewModel = viewModel, navController = navController, editable = true)
    }
}

@Preview
@Composable
private fun PreviewSelectionView() {
    TaskTrackerTheme {
        Surface {
            val viewModel = MainViewModel()
            viewModel.addTask(
                Task (
                    title = "Go shopping",
                    description = "Get eggs, bread and fruits",
                    isPriority = true
                )
            )
            SelectionView(viewModel = viewModel, navController = rememberNavController())
        }
    }
}