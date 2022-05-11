package com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* Repository에서 application context가 필요하기 때문에 AndroidViewModel을 상속받고 액티비티에서 뷰모델에
* application 파라미터를 넘기게 하기 위해 팩토리 메서드 작성 */
class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(application)

    // 뷰모델에 파라미터를 넘기기 위해 파라미터를 포함한 Factory 객체를 만들기 위한 클래스
    class Factory(private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            UserViewModel(application) as T
    }

    // 새 유저 정보가 추가될 때 옵저버가 감지해서 updateUserList()를 호출하기 때문에 자동으로 뷰가 갱신됨
    // 액티비티에 있던 유저를 추가하는 메서드를 뷰모델로 옮겨서 Repository에서 삽입 작업을 하게 한다. 이 때 String 변수 2개를 받는다
    fun addUser(name: String, age: String) {
        val user = User(name, age)

        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(user)
        }
    }

    fun getAll(): LiveData<List<User>> = repository.getAll()

    fun update(name: String, age: String) = repository.update(User(name, age))

    fun delete(name: String, age: String) = repository.delete(User(name, age))

    // 유저 정보 삭제 시 옵저버가 감지해서 updateUserList()를 호출하기 때문에 자동으로 뷰가 갱신됨
    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAll()
        }
    }

}