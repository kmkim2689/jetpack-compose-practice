package com.practice.jetpack_compose_practice

// 추상화하기 위하여 인터페이스 사용. 재사용성과 유지보수성을 높이기 위함
// Key, Item 제네릭을 이용 => 자료형은 미정이기 때문에 임의의 값을 쓸 수 있다는 것이 Generic의 특성
// Key에는 특정 자료형이 사용될 것이고, Item으로도 특정 자료형이 사용될 것임.
interface Paginator<Key, Item> {
    // 페이징을 위해서는, 2개의 메소드가 필요.
    
    // 페이지가 끝에 다다랐을 때 다음 페이지를 불러오기 위한 메소드
    suspend fun loadNextItems()
    
    // 리프레쉬 등의 상황을 위한 초기화 메소드
    fun reset()
}