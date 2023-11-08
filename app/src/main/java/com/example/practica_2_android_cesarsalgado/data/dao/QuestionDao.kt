package com.example.practica_2_android_cesarsalgado.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practica_2_android_cesarsalgado.data.entity.Question

@Dao
interface QuestionDao {
    @Query("select * from Question where id = :id")
    fun get(id: Int): Question

    @Query("select * from Question")
    fun getAll(): List<Question>

    @Insert
    fun insert(question: Question): Unit

    @Query("delete from Question where id = :id")
    fun delete(id: Int): Unit

    @Query("update Question set description = :description, description = :description, correctAnswerIndex = :correctAnswerIndex where id = :id")
    fun update(id: Int, description: String, correctAnswerIndex: Int): Unit
}