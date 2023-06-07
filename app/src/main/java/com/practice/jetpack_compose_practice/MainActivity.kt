package com.practice.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val constraints = ConstraintSet {
                // in constriant set scope :
                // to "define the constraints"
                // create the references for each composable,
                // to constrain in the layout
                // 레이아웃에서 제약을 걸어줄 변수를 선언하고, createRefFor(id)를 이용하여
                // 제약을 걸어주고 싶은 composable의 아이디를 여기서 설정한다. 문자열 형태로 넣는다.
                // xml의 id 속성과 같다고 생각하면 됨. 즉 createRefFor는 뷰의 아이디를 설정하는 메소드
                // 제약을 걸어주고 싶은 composable마다 해당 작업을 수행해야 한다.
                val greenBox = createRefFor("greenbox")
                val redBox = createRefFor("redbox")

                // guidelines and barriers
                val guideline = createGuidelineFromTop(0.5f) // 50% of the top

                // 이렇게 제약을 걸어줄 변수들을 선언한 다음,
                // constrain() 함수를 이용하여 제약조건을 명시한다.
                // 제약을 걸어줄 ref 변수들을 안에 이용함으로써, 실제로 제약조건을 명시
                // 제약 조건은 중괄호 scope 안에 작성
                constrain(greenBox) {
                    // 만약 greenBox를 맨 위 왼쪽에 붙이고 싶다면?
                    // top of the parent, start of the parent
                    // 제약 위치를 명시하는 함수 : linkTo() 함수

                    top.linkTo(guideline)  // guideline으로 제약조건을 설정하는 경우, 'top'이 가이드라인에 붙음...
                    // 해석 : top(<=첫번째로 나오는 top. 즉 greenbox를 의미)
                    // totop(<=두 번째로 나오는 top, 즉 어디에 붙일 것인지를 의미)
                    // of parent(<= parent)
                    start.linkTo(parent.start)

                    // 너비와 높이도 설정 가능
                    // constraint layout내부의 뷰의 width, height 설정 가능
                    // 설정 : Dimension.단위
                    // ex : percent, value(dp 단위로 직접 설정), wrapContent, fillToConstraints(0dp와 같은 효과)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                constrain(redBox) {
                    top.linkTo(parent.top)
                    start.linkTo(greenBox.end)
//                    width = Dimension.fillToConstraints
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                // 뷰끼리 chain을 형성하는 방법(서로가 서로에게 묶여있는 형태)
                // create horizontal chain : endtostart, starttoend
                // chain style : packed(중앙에 몰림), spread, spareadInside
                //
                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Spread)

                // 여기까지 작업만 수행하면, constraint set만 설정했지
                // 아직 constraint layout이 정의되지 않았기 때문에
                // 아무것도 보이지 않을 것임임            
            }
            
            // ConstraintLayout을 정의함으로써 constraintset 사용 가능
            // 첫번째 인자로 constraint set 변수를 삽입
            ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                // layoutId메소드를 통하여, constraintset에서 설정한 아이디와 같은 것을 찾아
                // 제약조건을 해당 composable에 적용!!!
                Box(modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenbox")) {

                }

                Box(modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redbox")) {

                }
                
            }

        }
    }
}

/*
* constraint layout in jetpack compose
constraint set을 만들고, 그것을 constraint layout에 할당
row와 column을 nesting 하여 위치를 설정하여 사용하는 것보다는 이것을 사용하는 것이 훨씬 효율적일 것이다.
*
* Create references for each composable in the ConstraintLayout using the createRefs() or createRefFor()
Constraints are provided using the constrainAs() modifier, which takes the reference as a parameter and lets you specify its constraints in the body lambda.
Constraints are specified using linkTo() or other helpful methods.
parent is an existing reference that can be used to specify constraints towards the ConstraintLayout composable itself.
* */