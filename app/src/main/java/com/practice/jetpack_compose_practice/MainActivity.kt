package com.practice.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            단순히 Snackbar만 선언해놓으면, 상단에 스낵바가 띄워지고 사라지지 않는다.
//            하지만, 스낵바는 하단에 나타나고 일정 시간이 지나면 사라져야 함.
//            Snackbar {
//                Text(text = "Hello")
//            }

//            어디서 얼마나 스낵바가 띄워질지 결정하고 싶다면, Snackbar를 사용하여 세부사항을 설정하면 됨
//            하지만, 보통 사용되는 스낵바처럼 동작하기를 원한다면, Scaffold라는 레이아웃을 사용하면 된다.
//            Scaffold는 jetpack compose에서 이미 material design에서 존재하는 레이아웃 디자인들을 갖다가 쓸 수 있도록 함
//            Topbar, toolbar, snackbar, navigation bar 등등 갖다 쓸 수 있다.

            val scaffoldState = rememberScaffoldState()
//            여기서 textFieldState의 자료형은 MutableState이기 때문에,
//            만약 string값을 사용하고 싶다면 .value를 사용해야 한다.
//            val textFieldState = remember {
//                mutableStateOf("")
//            }

//            반면, by를 사용하고 var로 변경하면 자료형이 string이 된다. => delegation
//            즉, 바로 변수 명으로 바로 문자열을 사용할 수 있다는 의미.
//            여기서 이점이 존재함.
            var textFieldState by remember {
                mutableStateOf("")
            }

            val scope = rememberCoroutineScope()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
//                scaffoldState : 스낵바를 이용하기 위하여 설정
                scaffoldState = scaffoldState
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)) {
//                    textfield의 종류 : textfield, outlinedtextfield, basictextfield
//                    textfield에 적힌 값을 사용하기 위해서는, state의 값과 연결해주는 것이 필요함.
                    OutlinedTextField(value = textFieldState, label = {
//                      hint를 여기서 정의하면 된다.
                      Text("Enter your name")
                    }, onValueChange = {
//                        update state here
                        textFieldState = it
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        // show snackbar
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                        }

                    }) {
                        Text(text = "Greet me!")
                    }
                }

            }

        }
    }
}

/*
textfield : EditText 대신 사용
어떻게 compose에서 snackbar를 보여주는가?
그냥 Snackbar Composable를 사용하면 된다.
Jetpack Compose에서 scope.launch {}는 코루틴을 생성하고 비동기 작업을 실행하는 데 사용됩니다.
스낵바는 화면에 잠시 표시되고 사라지는 시간 제한이 있는 임시 메시지이므로,
스낵바를 표시하는 동안 사용자 인터랙션을 차단하지 않고 비동기적으로 처리하는 것이 적합합니다.

scope.launch {} 블록 내에서 스낵바를 표시하는 것은
Scaffold 컴포넌트와 연결된 ScaffoldState에 접근하여 스낵바를 표시하기 위한 방법입니다.
 ScaffoldState는 스낵바와 같은 상태를 관리하는 데 사용되는 Compose의 내장 상태입니다.
 scaffoldState.snackbarHostState.showSnackbar() 메서드를 호출하여 스낵바를 표시할 수 있습니다.

비동기적인 스낵바 표시를 위해 scope.launch {} 블록 내에서 스낵바를 표시하면, 코루틴을 통해 비동기 작업이 실행되고 화면에 스낵바가 표시됩니다. 이렇게하면 UI를 차단하지 않고 비동기 작업을 처리할 수 있으며, 사용자는 스낵바를 볼 수 있습니다.

 */