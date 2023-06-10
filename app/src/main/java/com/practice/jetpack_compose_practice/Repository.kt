package com.practice.jetpack_compose_practice

import android.app.LauncherActivity
import androidx.compose.material.ListItem
import kotlinx.coroutines.delay

class Repository {
    private val remoteDataSource = (1..100).map {
        ListItem(
            title = "Item $it",
            description = "Description $it"
        )
    }

    // 페이지의 끝에 다다랐을 때, 페이지 하나의 데이터 리스트를 얻어오기 위한 메소드
    // 한 페이지의 데이터를 얻어오기 위한 네트워크 통신에 비유 가능
    suspend fun getItems(page: Int, pageSize: Int): Result<List<ListItem>> {
        delay(2000L)
        // 시작하는 페이지의 인덱스(몇 번째 인덱스부터 한 번 불러오는 리스트의 시작인지)
        val startingIndex = page * pageSize
        // 데이터 전체 크기보다 현재 표출된 데이터의 개수가 '적거나 같아야' 페이징 요청의 결괏값을 반환
        // 실패 시
//        Result.failure()
        // 성공 시
        return if (startingIndex + pageSize <= remoteDataSource.size) {

            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize)
            )
            // 만약 현재 표출된 데이터의 개수가 전체 데이터 수보다 많으면, 빈 리스트를 줌으로써 중단
        } else Result.success(emptyList())
    }
}