package com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    // MVVM 패턴을 적용할 것이기 때문에 List<User> -> LiveData<List<User>>로 변경
    @Query("SELECT * FROM User")
    fun getAll(): LiveData<List<User>>

    @Query("DELETE FROM User")
    fun deleteAll()
}