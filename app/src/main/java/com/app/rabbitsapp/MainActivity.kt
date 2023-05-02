package com.app.rabbitsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.app.rabbitsapp.data.Rabbit
import com.app.rabbitsapp.ui.theme.RabbitsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RabbitsAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = viewModel()
                    val rabbit = viewModel.state.value.rabbit
                    val isLoading = viewModel.state.value.isLoading

                    // the below condition means - if the "rabbit" is not null then execute the code written inside let braces
                    rabbit?.let {
                        // Image to show the picture of the rabbit
                        Image(painter = rememberImagePainter(
                            data = rabbit.imageUrl,
                            builder = {crossfade(true)}
                        ), contentDescription = "Rabbit")

                        // Space below the image
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Text for rabbit name
                        Text(text = rabbit.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)

                        // Space below the name
                        Spacer(modifier = Modifier.height(8.dp))

                        // Text for rabbit description
                        Text(text = rabbit.description, fontSize = 16.sp)

                        // Space below the description
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    
                    // Show button outside of the let
                    Button(onClick = viewModel::getRandomRabbit,
                        modifier = Modifier.align(Alignment.End)
                        ) {
                        Text(text = "Next rabbit")
                    }

                    // Space
                    Spacer(modifier = Modifier.height(8.dp))

                    if (isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}