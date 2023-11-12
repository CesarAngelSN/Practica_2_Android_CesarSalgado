package com.example.practica_2_android_cesarsalgado.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practica_2_android_cesarsalgado.data.entity.User

@Dao
interface UserDao {
    @Query("select * from User where id = :id")
    fun getById(id: Int): User

    @Query("select * from User where name = :name")
    fun getByName(name: String?): User

    @Query("select 'games played' from User where name = :name")
    fun getGamesPlayed(name: String?): Int

    @Query("select 'total correct answers' from User where name = :name")
    fun getCorrectAnswers(name: String?): Int

    @Query("select * from User")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User): Unit

    @Query("delete from User where id = :id")
    fun deleteById(id: Int): Unit

    @Query("delete from User where name = :name")
    fun deleteByName(name: String?): Unit

    @Query("update User set name = :name, password = :password where id = :id")
    fun update(id: Int, name: String, password: String): Unit

    @Query("update User set 'games played' = 'games played' + 1 where name = :name")
    fun updateGamesPlayed(name: String?): Unit

    @Query("update User set 'total correct answers' = 'total correct answers' + 1 where name = :name")
    fun updateCorrectQuestionsAnswered(name: String?): Unit
}