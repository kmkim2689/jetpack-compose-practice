package com.practice.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column(Modifier.fillMaxSize()) {
//                자식 Composable에도 사용되는 state 변수를 정의 가능. external state
                val color = remember {
                    mutableStateOf(Color.Yellow)
                }
//            weight : Column/row 안에서 공간을 차지하는 비중
                ColorBox(modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()) {
                    color.value = it
                }

                Box(modifier = Modifier
//                  또다른 Box를 만들려고 하는데, 그런데 Colorbox에서 선언하였던 color state변수를 이용하고자 함.
//
                    .background(color.value)
                    .weight(1f)
                    .fillMaxSize()) {

                }
            }

        }
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier, updateColor: (Color) -> Unit) {
//    1. state 변수 선언하는 방법 : mutableStateOf()
//    이 변수의 값이 변경되면, 이 변수가 속한 해당 Composable 함수는 처음부터 끝까지 다시 실행된다.
//    따라서, composable 안에서 해당 변수를 정의하면 항상 노란색이 뜨므로 이는 적절하지 못한 방법이다.
//    val color = mutableStateOf(Color.Yellow)
//    이 때 사용할 수 있는 방법이 remember를 사용하는 것이다.
//    remember : lambda function that comes from compose,
//    it will just remember the value of the state from the last composition.
//    이것을 통해, 재렌더링이 일어나도 color의 값은 Yellow로 초기화가 되지 않게 된다.
//    val color = remember {
//        mutableStateOf(Color.Yellow)
//    }

    Box(modifier = modifier
//       2. state 변수의 value를 조회하는 방법 : state변수.value 키워드 이용
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
//            3. 클릭하였을 때, Box의 Background 색상이 변화하도록 하고 싶다면?
//            이 때 state를 사용하면 된다. 상황에 따라 렌더링을 다르게 하고 싶다면 사용하는 것이 바로 state
//            state 변수의 값을 '재할당'한다. 그러면 색상이 바뀌게 된다.(recomposing -> state change -> rerendering)
//            mvvm에서 사용하는 livedata의 원리와 동일하다...
//            랜덤 컬러를 설정하기 위하여, Random 클래스 이용

        }) {


    }
}