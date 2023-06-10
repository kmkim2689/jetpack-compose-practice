package com.practice.jetpack_compose_practice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    // Repository에 접근하기 위한 클래스 : ViewModel

    // Repository에 의존하기 위하여... 사용
    // 보통 프로젝트라면 의존성 주입(Hilt...)을 해야 하지만 여기서는 그냥 의존
    private val repository = Repository()

    // state 변수를 정의 => 밑에서 정의한 데이터클래스의 값에 따라 화면에 즉각 반영하기 위함
    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        // 콜백 작업들을 여기서 실제로 구현...
        // !!!mutable state 변수이기 때문에 값의 변화에 따라 즉각적으로 화면에 반영됨
        onLoadUpdated = {
            // DefaultPaginator에서 현재 정의되어 있는 로딩 여부 Boolean값에 따라 state를 정의
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getItems(nextPage, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        // 통신 성공 시, 기존 아이템 리스트에 새롭게 얻어온 아이템을 추가
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                // endReached 여부는 네트워크 통신했을 때 빈 리스트를 반환받았는지 여부로 따지게 됨
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}

// 현재 화면의 상태에 대한 데이터 클래스
data class ScreenState(
    val isLoading: Boolean = false,
    // 현재 리스트
    val items: List<ListItem> = emptyList(),
    // 에러 메시지
    val error: String? = null,
    // 페이지의 끝에 다다랐는가
    val endReached: Boolean = false,
    // 현재 페이지 : 초기에는 제로
    val page: Int = 0
)