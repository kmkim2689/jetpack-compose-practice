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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.jetpack_compose_practice.ui.theme.JetpackcomposepracticeTheme
import com.practice.jetpack_compose_practice.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        상황에 따라 다른 종류의 font를 사용하기 위하여 FontFamily를 이용
        val fontFamily = FontFamily(
//            fontweight가 thin으로 설정되었을 때 사용할 폰트
            Font(R.font.playfair_italic_variablefont, FontWeight.Thin),
            Font(R.font.playfair_variablefont, FontWeight.Light),
//            ctrl+d를 하면 해당 코드를 붙여넣을 수 있음
        )

        setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))) {
                Text(
//                    annotatedString을 통하여, 텍스트의 일부만 스타일링 가능
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Green,
                                fontSize = 50.sp
                            )
                        ) {
//                            withStyle에서 정의한 스타일을 적용하기 위하여,
//                            withStyle 내부의 블록에서 append를 통하여 해당 스타일을 적용할 문자열을 정의
                            append("J")
                        }
//                        withStyle에서 설정한 스타일을 적용하지 않기를 원하면, 최상단에서 append
//                        기본스타일, 즉 아래의 스타일들이 적용될 것이다.
                        append("etpack ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Green,
                                fontSize = 50.sp
                            )
                        ) {
//                            withStyle에서 정의한 스타일을 적용하기 위하여,
//                            withStyle 내부의 블록에서 append를 통하여 해당 스타일을 적용할 문자열을 정의
                            append("C")
                        }
                        append("ompose")
                    },
//                text styling
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    fontStyle = FontStyle.Italic,
//                    textAlign 속성 : 부모 요소 내부에서 정렬되는 방법 정의
                    textAlign = TextAlign.Center,
//                    textDecoration을 통하여 꾸밀 수 있음
                    textDecoration = TextDecoration.Underline
                )

            }
        }
    }
}

// Compose에서 Text를 스타일링 하는 방법
/*
* Font를 사용하는 방법
* 1. google fonts로 들어간다.(fonts.google.com)
* 2. 특정 폰트를 선택한다.
* 3. Download family 클릭하여 다운로드
* 4. 글꼴파일을 프로젝트의 font 디렉토리에 붙여넣는다.
*  => resource directory를 제작. 이름은 font로 한다.
* 이 때 주의할 점은, 파일명이 모두 소문자로 되어있어야 하며, -는 _로 대체되어야 한다.
* _ 이외의 모든 특수문자는 배제
* android studio resource file명의 convention이기 때문
*
* *** annotatedString을 통하여, 텍스트의 특정 부분만의 스타일을 설정할 수도 있다.
* 텍스트의 일부 글자만 스타일링 하는 것은 기존의 xml로도 가능은 했지만,
* 매우 불편한 방식으로 해야했는데 Jetpack Compose에서는 이를 편리하게 가능
* */
