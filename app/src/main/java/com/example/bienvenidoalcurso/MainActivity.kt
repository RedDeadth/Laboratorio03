import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var switchState by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableStateOf(0f) }
    var textFieldValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Todos los elementos") }) },
        bottomBar = {
            BottomAppBar {
                Text("Bottom Navigation")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Text("Lazy Column", style = MaterialTheme.typography.headlineSmall)
                LazyRow {
                    items(5) { index ->
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("Item $index")
                            }
                        }
                    }
                }
            }

            item {
                Text("Grid", style = MaterialTheme.typography.headlineSmall)
                Row(Modifier.fillMaxWidth()) {
                    repeat(2) { row ->
                        Column(Modifier.weight(1f)) {
                            repeat(2) { col ->
                                Card(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .aspectRatio(1f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text("Cell ${row * 2 + col}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Button(onClick = { showDialog = true }) {
                    Text("Show AlertDialog")
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("This is a Card", modifier = Modifier.padding(16.dp))
                }
            }

            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = switchState,
                        onCheckedChange = { switchState = it }
                    )
                    Text("Checkbox")
                }
            }

            item {
                Icon(Icons.Filled.Add, contentDescription = "Icon")
            }

            item {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            item {
                RadioButton(
                    selected = switchState,
                    onClick = { switchState = !switchState }
                )
            }

            item {
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Switch(
                    checked = switchState,
                    onCheckedChange = { switchState = it }
                )
            }

            item {
                Divider()
            }

            item {
                DropdownMenu(
                    expanded = false,
                    onDismissRequest = { }
                ) {
                    DropdownMenuItem(
                        text = { Text("Option 1") },
                        onClick = { }
                    )
                }
            }

            item {
                OutlinedTextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text("OutlinedTextField") }
                )
            }

            item {
                Text("This would be a Pager", style = MaterialTheme.typography.bodyLarge)
            }

            item {
                Button(onClick = { /* Show Snackbar */ }) {
                    Text("Show Snackbar")
                }
            }

            item {
                TabRow(selectedTabIndex = 0) {
                    Tab(selected = true, onClick = { }) {
                        Text("Tab 1")
                    }
                    Tab(selected = false, onClick = { }) {
                        Text("Tab 2")
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Alert Dialog") },
            text = { Text("This is an alert dialog.") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MainScreen()
    }
}