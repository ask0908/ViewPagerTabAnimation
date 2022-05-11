package com.ulternativetechnology.kotlintestproject.retrofitWithRoomDataBinding.room

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* DB를 자주 생성하는 건 비효율적이기 때문에 싱글톤 패턴으로 구현하는 게 권장됨
* entities : 사용할 엔티티 선언, version : 엔티티 구조 변경 시 구분 기준 */
@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase? {
            if (instance == null) {
                synchronized(UserDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user.db"
                    ).build()
                }
            }
            return instance
        }
    }
}