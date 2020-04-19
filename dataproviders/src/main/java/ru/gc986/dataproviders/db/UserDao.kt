package ru.gc986.dataproviders.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.gc986.models.user.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("DELETE FROM user")
    fun deleteAll()

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

}