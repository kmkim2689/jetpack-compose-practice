package com.practice.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.jetpack_compose_practice.ui.theme.JetpackcomposepracticeTheme
import com.practice.jetpack_compose_practice.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            jetpack compose에서 image resource를 사용하려면, painterResource라는 메소드를 이용
            val painter = painterResource(id = R.drawable.sharehands_logo)
            val description = "it is the logo"
            val title = "sharehands logo"
            Box(modifier = Modifier
                .fillMaxWidth(0.5f)) {
                ImageCard(painter = painter, contentDescription = description, title = title)
            }

        }
    }
}

// 재사용 가능한 Composable을 제작
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
//            여기에 다른 composable들을 정의하면, 위에 겹쳐 쌓이게 된다.
//            즉 가장 먼저 쓰이는 것이 가장 밑으로 가게 되는 것.
//            이미지(Image)부터 작성하고, 그 다음에 글을 작성해야 할것.
            Image(painter = painter, 
                contentDescription = contentDescription,
//                ContentScale.Crop : centerCrop와 유사
            contentScale = ContentScale.Crop)

//            이미지 위에 gradient를 주기 위하여, 새로운 box를 또 만들어 거기에 gradient 적용
            Box(modifier = Modifier.fillMaxSize()
//                    음영을 주려면, background에 Brush를 두면 된다.
                .background(Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent, Color.Black
                    ),
                    startY = 300f
                ))) {

            }
            
//            여기에 바로 Text를 정의하면, 정렬하기가 힘들다. 따라서 Box를 다시 한 번 정의하고
//            그 Box를 정렬하는 것이 좋음
//            fillmaxsize를 통하여 부모 box를 모두 채우도록 함
//            contentAlignment를 이용하여 box가 부모 요소에서 어디 위치에 있을 것인지 결정
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}

// Painter 자료형 : image resources를 사용(display)할 수 있도록 함
// composable 함수에 modifier도 넘겨줄 수 있다. 이 과정에서 빈 modifier를 default로 넘겨줄 수도 있다.
/*
여기에서 기본적으로 제공되는 Card Composable을 이용한다.
=> XML에서 사용되는 Card 뷰와 유사
modifier의 경우 함수의 인자로 넘겨받은 modifier를 이용한다.(Modifier가 아님)
shape : 말 그대로 카드의 shape을 정의.
elevation : 그림자
여기서 만들고 싶은 것은 사진이 카드를 꽉 채우고, 사진 위에서 카드의 밑 부분에 설명글이 적혀있기를 원함.
이 과정에서 column을 사용하는 것은 부적절할 것
이 때, Box라는 Composable을 이용하는 것이다.
 */