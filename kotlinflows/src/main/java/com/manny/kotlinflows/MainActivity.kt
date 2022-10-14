package com.manny.kotlinflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.manny.kotlinflows.ui.theme.AndroidExamplesTheme
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidExamplesTheme {
                val viewModel = viewModels<MainViewNodel>()
                var time = viewModel.value.countDownFlow.collectAsState(initial = 10)
                // A surface container using the 'background' color from the theme
                Box(

                ) {
                   // Greeting("Android")

                    Show(name = time.value.toString())
                  //  Show(name = now.toString())
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Show(name: String){
    Text(text = name,
        fontSize = 30.sp,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidExamplesTheme {
        Show("Android")
       // Greeting("Android")
    }
}