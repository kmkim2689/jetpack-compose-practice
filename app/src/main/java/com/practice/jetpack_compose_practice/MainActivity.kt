package com.practice.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practice.jetpack_compose_practice.ui.theme.JetpackcomposepracticeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Jetpack Compose에서 ViewModel 사용을 위하여,
        // dependencies 추가 필요
        // implementation "androidx.lifecycle:lifecycle-viewmodel-compose:$version"'

        setContent {
            val viewModel = viewModel<MainViewModel>()
            val state = viewModel.state

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.items.size) { i ->
                    val item = state.items[i]
                    if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                        viewModel.loadNextItems()
                    }
                    // 아이템에 대한 레이아웃 생성
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                        Text(
                            text = item.title,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.description
                        )
                    }
                }

                // loading layout
                item {
                    if (state.isLoading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

        }
    }
}


