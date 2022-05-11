package com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.util

/* sealed class : 부모 클래스를 상속받는 자식 클래스의 종류를 제한하는 특성을 가진 클래스
* 같은 파일에 정의된 자식 클래스(data class, object class, class) 외에 다른 하위 클래스는 없다고 컴파일러에게 알려줌 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}