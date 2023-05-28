package com.practice.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.jetpack_compose_practice.ui.theme.JetpackcomposepracticeTheme
import com.practice.jetpack_compose_practice.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth()
                    .padding(5.dp)
                    .border(5.dp, Color.Magenta, RectangleShape)
                    .padding(5.dp)
                    .border(5.dp, Color.Blue)
                    .padding(5.dp)
                    .border(10.dp, Color.Red)
                    .padding(10.dp)
            ) {
                Text(text = "Hello", modifier = Modifier
                    .clickable {

                    })
//                    .border(5.dp, Color.Yellow)
//                    .padding(5.dp)
////                        offset의 순서가 중간에 끼었기 때문에, 노란색 테두리가 들어간 다음에 글자가 이동. 글자가 이동하며 노란색 테두리는 이동하지 않음.
//                    .offset(20.dp, 20.dp)
//                    .border(10.dp, Color.Black)
//                    .padding(10.dp))
//                    modifier = Modifier
        // 좌우(양수 : 우측, 음수 : 좌측), 상하(양수 : 아래, 음수 : 위) 순서
//                    .offset(50.dp, 20.dp))
//                pushing down by giving Spacer with height
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "World")
            }
        }
    }
}

/*
size(width, height)뿐만 아니라, 정말 많은 스타일들을 Modifier를 통해 설정할 수 있다.
- padding
- margin
- click events(clickable)
- dragging events(draggable)
- zoomable
- scrollable
...
 */