package com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ulternativetechnology.kotlintestproject.R
import com.ulternativetechnology.kotlintestproject.databinding.ActivityRoomDataBindingBinding
import com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room.User
import com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

/* https://hanyeop.tistory.com/198?category=985684 */
class RoomDataBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomDataBindingBinding
//    private lateinit var db: UserDatabase
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_data_binding)

        // []는 get()의 변형된 표기법이다
        userViewModel = ViewModelProvider(this, UserViewModel.Factory(application))[UserViewModel::class.java]
        /* XML에 있는 userViewModel 객체에 위에서 초기화한 뷰모델 객체를 대입 */
        binding.userViewModel = userViewModel

        // 옵저버가 리스트 변화 감지
        userViewModel.getAll().observe(this) {
            updateUserList(it)
        }

//        // DB 연결
//        db = UserDatabase.getInstance(applicationContext)!!
//
//        // 옵저버가 리스트 변화를 감지해서 업데이트 함수(updateUserList()) 호출
//        db.userDao().getAll().observe(this) {
//            updateUserList(it)
//        }
    }

    /* 리스트를 받아서 뷰에 표시 */
    private fun updateUserList(userList: List<User>) {
        var userListText = "사용자 목록"

        for (i in userList) {
            userListText += "\n${i.id} ${i.name}, ${i.age}"
        }
        binding.textView.text = userListText
//        CoroutineScope(Dispatchers.Main).launch {
//            val load = async(Dispatchers.IO) {
//                for (i in userList) {
//                    userListText += "\n${i.id} ${i.name}, ${i.age}"
//                }
//            }
//            load.await()
//            binding.textView.text = userListText
//        }
    }

//    /* 새 유저 정보 추가 시 옵저버가 감지해서 updateUserList()를 호출하기 때문에 자동으로 뷰가 갱신됨 */
//    private fun addUser(view: View) {
//        val user = User(binding.nameEditView.text.toString(), binding.ageEditView.text.toString())
//
//        CoroutineScope(Dispatchers.IO).launch {
//            db.userDao().insert(user)
//        }
//    }
//
//    /* 유저 정보 삭제 시 옵저버가 감지해서 updateUserList()를 호출하기 때문에 자동으로 뷰가 갱신됨 */
//    private fun deleteUser(view: View) {
//        CoroutineScope(Dispatchers.Main).launch {
//            val delete = async(Dispatchers.IO) {
//                db.userDao().deleteAll()
//            }
//            delete.await()
//        }
//    }

}