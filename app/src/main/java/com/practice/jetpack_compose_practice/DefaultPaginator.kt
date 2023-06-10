package com.practice.jetpack_compose_practice

class DefaultPaginator<Key, Item>(
    // 여기서는 Key는 페이지 넘버에 대응되며, Item은 ListItem 클래스에 대응된다.
    // paging을 위하여 필요한 값들과 메소드들
    // 1. initialKey : the initial page we want to start with.
    // 예컨대, 만약 1페이지까지 로드가 완료된 상태라면 다음으로는 2를 보내야 할 것임.
    // 이 때, 2페이지를 불러오기 위해 initialKey의 값은 2가 넘어오게 될 것이다.
    private val initialKey: Key,
    // 이하는 콜백 메소드임. 이 메소드들은 변할일이 없으므로 상수처럼 사용 가능
    // 따라서, inline 키워드를 이용하여 컴파일 시간에 특정 변수를 참조하지 않고 그 변수의 값을 직접 복사하여 즉각적인 값을 읽을 수 있도록 함
    // 2. onLoadUpdated : 로딩이 끝나고 가져온 값들을 리스트에 업데이트까지 완료하였을 때의 콜백
    private inline val onLoadUpdated: (Boolean) -> Unit,
    // 3. how we can get the next load of items
    // define the way to get new data with the given 'Key' => Repository 결과에 대응되는 반환값.
    // 다만 여기서는 ListItem이 아닌 Generic Type으로 명시된 Item을 사용. 이는 일반화를 통하여 재사용성을 높이기 위함.
    // 이러한 Request 메소드는 대개 네트워크 통신을 통해 이뤄지므로 suspend 키워드를 사용
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    // 4. next key(페이지)를 얻기 위한 메소드
    // 여기 예제에서는 그냥 다음 페이지는 1을 더하면 되겠지만, 만약 복잡한 로직이 필요하면 그에 맞는 함수가 필요할 것
    private inline val getNextKey: suspend (List<Item>) -> Key,
    // 5. 에러 발생 시 대처 메소드
    private inline val onError: suspend (Throwable?) -> Unit,
    // 6. 성공적으로 데이터를 가져왔을 때 메소드
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    // 멤버번수 : 현재 페이지에 대응
    private var currentKey: Key = initialKey
    // 멤버변수 : 현재 로딩이 되고 있는지...
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        // 로딩이 이뤄지고 있으면 다음 아이템을 가져오라는 요청을 굳이 할 필요가 없음...
        if (isMakingRequest) {
            return
        }

        // 로딩 상태를 true로 만든다.
        isMakingRequest = true

        onLoadUpdated(true)
        val result = onRequest(currentKey)
        // 네트워크 통신이 끝났으므로 다시 로딩 여부를 false로 만든다.
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            // 다음에 또 써야하기 때문에 원상태로 돌려놓는다.
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)

    }

    override fun reset() {
        currentKey = initialKey
    }

}