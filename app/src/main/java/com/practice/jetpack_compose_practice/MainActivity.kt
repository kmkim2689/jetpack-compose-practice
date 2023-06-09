package com.practice.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(scaffoldState = scaffoldState) {
                var counter by remember {
                    mutableStateOf(0)
                }
                if (counter % 5 == 0 && counter > 0) {
                    // 스낵바를 보여주는 코드는, 코루틴 내부에서 진행해야 한다.
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello")
                    }

                }
                Button(onClick = {counter++}) {
                    Text("Click me : $counter")

                }
            }

        }
    }
}

// 어떠한 연유로, global variable이 필요
// 또한, 전역변수 i의 값을 버튼을 누를 때 1 추가시키고 싶음
var i = 0

//@Composable
//fun MyComposable() {
//    // 만약 composable 함수 내부에서 값을 추가시킨다면,
//    // 이것은 side effect로, 좋지 못한 방법이다.
//    // i++
//
//    // 대신, SideEffect를 활용한다.
//    SideEffect {
//        // 블록 내부에 side effect인 동작을 명시한다.
//        // that will just make sure that this block code is called once this composable
//        // successfully recomposed basically after every successful composition
//        // if the composition fails, this block of code is not executed.
//        i++
//    }
//
//    Button(onClick = { /*TODO*/ }) {
//        Text(text = "Click Me!")
//
//    }
//}
//@Composable
//fun MyComposable(backPressedDispatcher: OnBackPressedDispatcher) {
//    val callback = remember {
//        object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // 뒤로가기 눌렀을 때의 동작을 명시
//            }
//
//        }
//    }
//
//
//
//    DisposableEffect(key1 = backPressedDispatcher) {
//
//        // backPressedDispatcher
//        backPressedDispatcher.addCallback(callback)
//        onDispose {
//            // 추후 backPressedDispatcher에 대한 callback을 제거하고 싶을 때
//            // onDispose 블록 스코프 내부에서 진행
//            callback.remove()
//        }
//    }
//
//    Button(onClick = {}) {
//        Text(text = "Click me")
//    }
//}