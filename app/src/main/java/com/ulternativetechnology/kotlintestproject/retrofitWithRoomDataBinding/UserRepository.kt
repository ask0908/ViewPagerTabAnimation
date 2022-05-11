package com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding

import android.app.Application
import androidx.lifecycle.LiveData
import com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room.User
import com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room.UserDao
import com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room.UserDatabase

/* 앱에서 쓰는 데이터와 데이터 통신을 하는 역할. 뷰모델은 DB에 직접 접근하지 않고 Repository를 만드는 것이 권장되기 때문에 이 클래스가 필요하다 */
class UserRepository(application: Application) {
    private val userDao: UserDao
    private val userList: LiveData<List<User>>

    init {
        val db: UserDatabase = UserDatabase.getInstance(application)!!
        userDao = db.userDao()
        userList = db.userDao().getAll()
    }

    fun insert(user: User) = userDao.insert(user)

    fun update(user: User) = userDao.update(user)

    fun delete(user: User)  = userDao.delete(user)

    fun getAll(): LiveData<List<User>> = userDao.getAll()

    fun deleteAll() = userDao.deleteAll()

}