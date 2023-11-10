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

    /*@Query("update User set gamesPlayed = gamesPlayed + :one where name = :name")
    fun updateGamesPlayed(name: String, one: Int): Unit

    @Query("update User set totalCorrectAnswers = totalCorrectAnswers + :one where name = :name")
    fun updateCorrectQuestionsAnswered(name: String, one: Int): Unit*/
}