package com.practice.jetpack_compose_practice

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollState = rememberScrollState()
            // 방법1. Lazy Column을 사용하지 않고 반복되는 아이템에 대한 스크롤을 구현하는 방법
            // 매우 비효율적
//            Column(
//                // Column에 이것을 작성해야 스크롤이 가능하다.
//                // verticalscroll 인자들
//                // 1. (scroll) state => 현재 scroll된 것에 관련한 state 관련 정보들을 담고 있음
//                modifier = Modifier.verticalScroll(state = scrollState)
//            ) {
//                for (i in 1..50) {
//                    Text(text = "Item $i", fontSize = 24.sp, fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 24.dp)
//                    )
//                }
//            }

            // 방법2. Lazy Column
            // 기본적으로 그 자체로 scroll이 가능하기 때문에, verticalscroll을 둘 필요가 없다.
            // 아이템 하나에 대한 반복문을 두어 블록 안에 추가할 필요가 없이, 이미 이러한 기능을 해주는 함수를 이용
            // 그런 함수가 바로 item(단일항목만 그리려고 할 때) 혹은 items(여러 개의 항목을 그려야 할 때, recyclerview에 대응)
            // 하지만 itemsIndexed 함수가 더욱 효율적임
            LazyColumn() {
//                items 함수의 인자
//                1. count : 얼마나 많은 개수를 넣을 것인지

//               itemsIndexed
//               매개변수 : 리스트. 보여줄 데이터들에 대한 리스트. 데이터클래스 보통 사용함.
                itemsIndexed(
                    listOf("this", "is", "jt", "compose")
                ) { index, string ->
                    Text(text = "$string", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                }

//                items() 이용하면 미리 count를 정해놓아야 함. 비효율적
//                items(5000) {
////                    블록 안에는 아이템 안에 들어갈 요소들을 넣으면 됨.
////                    여기서 it을 호출하면 그것은 인덱스를 나타냄.
//                    Text(text = "Item $it", fontSize = 24.sp, fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 24.dp)
//                    )
//                }
            }
        }
    }
}
/*
jetpack compose에서 recyclerview와 같은 기능을 구현하는 방법
:LazyColumn 혹은 LazyRow를 사용하면 훨씬 간편하게 구현 가능
LazyColumn : 여기서 Lazy라는 것은 Scroll되는 시점에서야 보여진다는 측면에서 사용되는 용어임.


rememberScrollState는 Jetpack Compose에서 스크롤의 상태를 기억하고 관리하기 위해 사용되는 함수입니다. 스크롤 가능한 컴포넌트(예: LazyColumn, LazyRow, ScrollableColumn, ScrollableRow 등)와 함께 사용됩니다.

rememberScrollState 함수는 스크롤의 현재 위치, 스크롤의 최소값 및 최대값, 스크롤 가능한 영역 등의 정보를 포함하는 ScrollState 객체를 생성합니다. 이 ScrollState 객체를 사용하여 스크롤을 제어하고 상태를 유지할 수 있습니다.

주요 사용 사례는 다음과 같습니다:

스크롤 위치 가져오기: scrollState.value를 통해 현재 스크롤 위치를 가져올 수 있습니다.
스크롤 위치 설정: scrollState.scrollTo() 함수를 사용하여 스크롤 위치를 지정할 수 있습니다.
스크롤 가능한 영역 제한: scrollState.minValue 및 scrollState.maxValue를 설정하여 스크롤 가능한 영역을 제한할 수 있습니다.
스크롤 이벤트 처리: scrollState를 감시하고 변경에 따라 필요한 작업을 수행할 수 있습니다.
 */